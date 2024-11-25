package testcases;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import coreUtilities.utils.FileOperations;
import io.restassured.response.Response;
import rest.ApiUtil;

public class RestAssured_TestCases_PL1 {

	FileOperations fileOperations = new FileOperations();

	private final String EXCEL_FILE_PATH = "src/main/resources/config.xlsx"; // Path to the Excel file
	private final String FILEPATH = "src/main/java/rest/ApiUtil.java";
	ApiUtil apiUtil;

	public static int appointmentId;

	@Test(priority = 1, groups = { "PL1" }, description = "Precondition: Create an appointment via the API\n"
			+ "1. Send POST request to create a new appointment with provided data\n"
			+ "2. Verify the response status code is 200 OK\n" + "3. Validate the response contains 'Status' as 'OK'\n"
			+ "4. Retrieve and validate the Appointment ID from the response")
	public void createAppointmentTest() throws Exception {

		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
		Map<String, String> postData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		apiUtil = new ApiUtil();
		Response response = apiUtil.createAppointmentWithAuth("/Appointment/AddAppointment", postData);

		// Validate method's source code
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"createAppointmentWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(response.statusCode(), 200, "Status code should be 201 Created.");

		// Validate key fields in the response
		String status = response.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Parse the "Results" object
		appointmentId = response.jsonPath().getInt("Results.AppointmentId");
		Assert.assertNotNull(appointmentId, "Appointment ID should not be null.");

		// Print the full response body
		System.out.println("Create Appointment Response:");
		response.prettyPrint();
	}

	@Test(priority = 2, groups = { "PL1" }, description = "Precondition: Multiple applicable doctors must exist\n"
			+ "1. Validate that the response contains list of Doctors\n"
			+ "2. Verify the response status code is 200.\n" + "3. Verify Performer IDs are unique")
	public void getAllApplicableDoctors() throws IOException {
		apiUtil = new ApiUtil();
		Response getAllDoctorsResponse = apiUtil.getAllApplicableDoctorsWithAuth("/Visit/AppointmentApplicableDoctors",
				null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"getAllApplicableDoctorsWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(getAllDoctorsResponse.statusCode(), 200, "Status code should be 200 OK.");

		String status = getAllDoctorsResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		List<Map<String, Object>> results = getAllDoctorsResponse.jsonPath().getList("Results");

		Assert.assertTrue(results.size() > 1, "Results should contain multiple doctors.");

		Set<Integer> performerIds = results.stream().map(result -> (Integer) result.get("PerformerId"))
				.collect(Collectors.toSet());

		Assert.assertEquals(performerIds.size(), results.size(), "Each doctor should have a unique PerformerId.");

		System.out.println("Appointment Applicable Doctors List Response:");
		getAllDoctorsResponse.prettyPrint();
	}

	@Test(priority = 3, groups = {
			"PL1" }, dependsOnMethods = "createAppointmentTest", description = "Precondition: An appointment must be created successfully.\n"
					+ "1. Validate that the appointment ID is not null.\n"
					+ "2. Send a PUT request to cancel the appointment using the appointment ID.\n"
					+ "3. Verify the response status code is 200.\n"
					+ "4. Validate the response indicates successful cancellation.")
	public void cancelAppointmentTest() throws IOException {
		apiUtil = new ApiUtil();

		Assert.assertNotNull(appointmentId, "Appointment ID should be set by the createAppointmentTest.");
		Response cancelResponse = apiUtil.cancelAppointmentWithAuth(
				"/Appointment/AppointmentStatus?appointmentId=" + appointmentId + "&status=cancelled", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"cancelAppointmentWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(cancelResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for cancellation
		String status = cancelResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		String resultMessage = cancelResponse.jsonPath().getString("Results");
		Assert.assertEquals(resultMessage, "Appointment information updated successfully.",
				"Message should confirm the update.");

		// Print the response from canceling the appointment
		System.out.println("Cancelled Appointment Response:");
		cancelResponse.prettyPrint();
	}

	@Test(priority = 4, groups = {
			"PL1" }, description = "Precondition: Patients and Doctor must be created successfully.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates the status of clashes in appointment as true or false.")
	public void clashAppointmentTest() throws Exception {
		String SHEET_NAME = "ClashAppointmentData"; // Sheet name in the Excel file
		Map<String, String> clashedData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
		apiUtil = new ApiUtil();

		String requestDate = clashedData.get("requestDate");
		String performerId = clashedData.get("performerId");
		String patientId = clashedData.get("patientId");

		Response updateResponse = apiUtil.clashAppoitnmentWithAuth("/Appointment/CheckClashingAppointment?patientId="
				+ patientId + "&requestDate=" + requestDate + "&performerId=" + performerId, null);

		Assert.assertEquals(updateResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for cancellation
		String status = updateResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		String resultMessage = updateResponse.jsonPath().getString("Results");
		Assert.assertEquals(resultMessage, "false", "Message should confirm the update.");

		// Print the response from canceling the appointment
		System.out.println("Successfully checked for now clashing appointment:");
		updateResponse.prettyPrint();
	}

	@Test(priority = 5, groups = {
			"PL1" }, description = "Precondition: Patients and Doctor must be created successfully.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successful display of all the users that contain the string in their name.")
	public void searchPatientTest() throws Exception {
		apiUtil = new ApiUtil();
		// Send request and get response
		Response searchedResponse = apiUtil.searchPatientWithAuth("/Patient/SearchRegisteredPatient?search=Test", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(searchedResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
		String firstName = searchedResponse.jsonPath().getString("Results[0].FirstName");
		String shortName = searchedResponse.jsonPath().getString("Results[0].ShortName");
		String lastName = searchedResponse.jsonPath().getString("Results[0].LastName");

		// Print the values to verify
		System.out.println("FirstName: " + firstName);
		System.out.println("ShortName: " + shortName);
		System.out.println("lastName: " + lastName);

		// Validate that 'firstName' and 'shortName' contain "Test"
		Assert.assertTrue(firstName.contains("Test"), "FirstName does not contain 'Test'");
		Assert.assertTrue(shortName.contains("Test"), "ShortName does not contain 'Test'");

		// Validate the 'Status' field
		String status = searchedResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Searched Patient Response:");
		searchedResponse.prettyPrint();
	}

	@Test(priority = 6, groups = {
			"PL1" }, description = "Precondition: Appointments must be made between current date and 5 days before the current date.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of appointments along with patient Id and Appointment time.")

	public void BookingListTest() throws Exception {
		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
		apiUtil = new ApiUtil();

		// Set date range
		LocalDate currentDate = LocalDate.now();
		LocalDate dateFiveDaysBefore = currentDate.minusDays(5);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		// Format dates as strings
		String currentDateStr = currentDate.format(formatter);
		String dateFiveDaysBeforeStr = dateFiveDaysBefore.format(formatter);
		String performerId = searchResult.get("performerId");

		// Send request and get response
		Response updateResponse = apiUtil.bookingListWithAuthInRange("/Appointment/Appointments?FromDate="
				+ dateFiveDaysBeforeStr + "&ToDate=" + currentDateStr + "&performerId=" + performerId + "&status=new",
				null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(updateResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = updateResponse.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		// Iterate over each result to print and verify the 'AppointmentDate'
		for (Map<String, Object> result : results) {
			String appointmentDateStr = result.get("AppointmentDate").toString().substring(0, 10); // Extract date
																									// portion only
			System.out.println("Appointment Date: " + appointmentDateStr);

			// Parse the 'AppointmentDate' to LocalDate for comparison
			LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);

			// Assert that 'AppointmentDate' is within the specified range
			Assert.assertTrue(!appointmentDate.isBefore(dateFiveDaysBefore) && !appointmentDate.isAfter(currentDate),
					"AppointmentDate " + appointmentDate + " is not within the expected range: " + dateFiveDaysBeforeStr
							+ " to " + currentDateStr);
		}

		// Validate the 'Status' field
		String status = updateResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Searched appointment  Response Within a Range:");
		updateResponse.prettyPrint();
	}

	@Test(priority = 7, groups = {
			"PL1" }, description = "Precondition: Appointments must be made between current date and 5 days before the current date.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of appointments along with patient Id and Appointment time.")

	public void AllStockDetailsTest() {
		apiUtil = new ApiUtil();
		Response stockDetails = apiUtil.AllStockDetailsWithAuth("/PharmacyStock/AllStockDetails", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(stockDetails.statusCode(), 200, "Status code should be 200 OK.");

		List<Map<String, Object>> results = stockDetails.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		// Iterate over each result to print and verify the 'AppointmentDate'
		for (Map<String, Object> result : results) {
			String ItemId = result.get("ItemId").toString(); // Extract date portion only
			System.out.println("Appointment Date: " + ItemId);

			// Assert that 'Item id' is not null
			Assert.assertNotNull(ItemId, "The item id is null");
		}

		String status = stockDetails.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Searched appointment  Response Within a Range:");
		stockDetails.prettyPrint();
	}

	@Test(priority = 8, groups = {
			"PL1" }, description = "1. Send a GET request to fetch Main Store from the Pharmacy Settings.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response has an Id corresponding to the store along with the name and store description.")

	public void MainStoreTest() {
		apiUtil = new ApiUtil();
		Response stockDetails = apiUtil.MainStoreDetailsWithAuth("/PharmacySettings/MainStore", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(stockDetails.statusCode(), 200, "Status code should be 200 OK.");

		String result = stockDetails.jsonPath().getString("Results");
		System.out.println("Results: " + result);

		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
		String Name = stockDetails.jsonPath().getString("Results.Name");
		String storeDesc = stockDetails.jsonPath().getString("Results.StoreDescription");

		String StoreId = stockDetails.jsonPath().getString("Results.StoreId");

		// Assert that 'name, store description and store Id' is not null
		Assert.assertNotNull(Name, "The Name is null and the store doesn't exist.");

		Assert.assertNotNull(storeDesc, "The store description is null and the store doesn't exist.");

		Assert.assertNotNull(StoreId, "The StoreId is null and the store doesn't exist.");

		String status = stockDetails.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Fetched Main Store from the Pharmacy Settings:");
		stockDetails.prettyPrint();
	}

	@Test(priority = 9, groups = {
			"PL1" }, description = "Precondition: Some Pharmacy Stores must be created already. \n"
					+ "1. Send a GET request to fetch whether we are able to fetch the pharmacy stores or not.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of name of the store along with Store Id.")

	public void PharmacyStoreTest() {
		apiUtil = new ApiUtil();
		Response pharmacyStoreResponse = apiUtil.PharmacyStoresWithAuth("/Dispensary/PharmacyStores", null);

		Assert.assertEquals(pharmacyStoreResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = pharmacyStoreResponse.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		// Iterate over each result to print and verify the 'AppointmentDate'
		for (Map<String, Object> result : results) {
			String StoreId = result.get("StoreId").toString();
			String Name = result.get("Name").toString(); // Extract date portion only
			System.out.println("StoreId: " + StoreId);// Extract date portion only
			System.out.println("Name: " + Name);

			// Assert that 'StoreId' is not null
			Assert.assertNotNull(StoreId, "The Store Id is null and the store doesn't exist.");
			// Assert that 'Name' is not null
			Assert.assertNotNull(Name, "The Name is null and the store doesn't exist.");
		}

		String status = pharmacyStoreResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following are the Pharmacy Stores:");
		pharmacyStoreResponse.prettyPrint();
	}

	@Test(priority = 10, groups = {
			"PL1" }, description = "1. Send a GET request to fetch whether we are able to fetch patient's consumption.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of patient name, patient id.")

	public void PatientConsumptionTest() {
		apiUtil = new ApiUtil();
		Response consumptionResponse = apiUtil.PatientConsumption("/PatientConsumption/PatientConsumptions", null);

		Assert.assertEquals(consumptionResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = consumptionResponse.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		// Iterate over each result to print and verify the 'AppointmentDate'
		for (Map<String, Object> result : results) {
			String PatientId = result.get("PatientId").toString();
			String PatientName = result.get("PatientName").toString(); // Extract date portion only
			System.out.println("PatientId: " + PatientId);// Extract date portion only
			System.out.println("PatientName: " + PatientName);

			// Assert that 'StoreId' is not null
			Assert.assertNotNull(PatientId, "The Patient Id is null and the store doesn't exist.");
			// Assert that 'PatientName' is not null
			Assert.assertNotNull(PatientName, "The Patient Name is null and the store doesn't exist.");
		}

		String status = consumptionResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following are the patient's consumption, Response:");
		consumptionResponse.prettyPrint();
	}

	@Test(priority = 11, groups = {
			"PL1" }, description = "Pre-conditions: Will require the counter Id and counterName to enter as a qeury paramter in the API. \n"
					+ "1. Send a PUT request to see whether we are able to activate the pharmacy counter.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of counter Id and counterName")

	public void ActivatePharmacyCountTest() throws Exception {
		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
		apiUtil = new ApiUtil();
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
		String counterId = searchResult.get("CounterId");
		String counterName = searchResult.get("CounterName");

		System.out.println("The counter id from the sheet is: " + counterId);
		System.out.println("The counter name from the sheet is: " + counterName);

		Response activationResponse = apiUtil.ActivatePharmCount(
				"/Security/ActivatePharmacyCounter?counterId=" + counterId + "&counterName=" + counterName, null);

		Assert.assertEquals(activationResponse.statusCode(), 200, "Status code should be 200 OK.");

		String result = activationResponse.jsonPath().getString("Results");
		System.out.println("Results: " + result);

		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
		String CounterName = activationResponse.jsonPath().getString("Results.CounterName");

		String CounterId = activationResponse.jsonPath().getString("Results.CounterId");

		// Assert that 'name, store description and store Id' is not null
		Assert.assertNotNull(CounterName, "The Counter Name is null and the store doesn't exist.");

		Assert.assertNotNull(CounterId, "The Counter Id is null and the store doesn't exist.");

		String status = activationResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Activated the pharmacy counter, Response :");
		activationResponse.prettyPrint();
	}

	@Test(priority = 12, groups = {
			"PL1" }, description = "1. Send a PUT request to fetch whether we are able to deactivate the pharmacy counter.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of status code as 200.")

	public void DeactivatePharmCountTest() throws Exception {
		apiUtil = new ApiUtil();
		Response consumptionResponse = apiUtil.DeactivatePharmCount("/Security/DeactivatePharmacyCounter", null);

		Assert.assertEquals(consumptionResponse.statusCode(), 200, "Status code should be 200 OK.");

		String result = consumptionResponse.jsonPath().getString("Results");
		System.out.println("Results: " + result);

		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
		String StatusCode = consumptionResponse.jsonPath().getString("Results.StatusCode");

		// Assert that 'name, store description and store Id' is not null
		Assert.assertTrue(StatusCode.equals("200"), "The status code is not 200 rather, " + StatusCode);

		String status = consumptionResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Deactivated pharmacy counter: Response");
		consumptionResponse.prettyPrint();
	}

	@Test(priority = 13, groups = {
			"PL1" }, description = "1. Send a GET request to fetch a list of Appointment Applicable Departments.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of department name, department id, department code")

	public void AppointApplicDeptTest() throws Exception {
		apiUtil = new ApiUtil();
		Response appointResponse = apiUtil.AppointApplicDept("/Master/AppointmentApplicableDepartments", null);

		Assert.assertEquals(appointResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = appointResponse.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		for (Map<String, Object> result : results) {
			String DepartmentId = result.get("DepartmentId").toString();
			String DepartmentName = result.get("DepartmentName").toString();
			System.out.println("DepartmentId: " + DepartmentId);
			System.out.println("DepartmentName: " + DepartmentName);
			System.out.println("\n");

			Assert.assertNotNull(DepartmentId, "The Department Id is null and the store doesn't exist.");
			Assert.assertNotNull(DepartmentName, "The Department Name is null and the store doesn't exist.");
		}

		String status = appointResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following is the list of Appointment Applicable Departments, Response:");
		appointResponse.prettyPrint();
	}

	@Test(priority = 14, groups = {
			"PL1" }, description = "1. Send a GET request to fetch a list of currently Admitted Patients Data.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successfull display of Patient Admission Id, Admitted Date but Discharged Date must be null")

	public void AdmittedPatientsData() throws Exception {
		apiUtil = new ApiUtil();
		Response admittedPatientResponse = apiUtil
				.admittedPatientData("/Admission/AdmittedPatientsData?admissionStatus=admitted", null);

		Assert.assertEquals(admittedPatientResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = admittedPatientResponse.jsonPath().getList("Results");
		System.out.println("Results: " + results);

		for (Map<String, Object> result : results) {
			String PatientId = result.get("PatientId").toString();
			String AdmittedDate = result.get("AdmittedDate").toString();

			System.out.println("PatientId: " + PatientId);
			System.out.println("AdmittedDate: " + AdmittedDate);
			System.out.println("\n");

			Assert.assertNotNull(PatientId, "The Patient Id is null and the store doesn't exist.");
			Assert.assertNotNull(AdmittedDate, "The Admitted Date is null and the store doesn't exist.");
			// Verify that DischargedDate is null
			Assert.assertNull(result.get("DischargedDate"), "DischargedDate should be null");

		}

		String status = admittedPatientResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following is the list of Admitted Patients Data, Response:");
		admittedPatientResponse.prettyPrint();
	}

	@Test(priority = 15, groups = { "PL1" }, description = "Precondition: Currency code must not already exist\n"
			+ "1. Validate the currency code\n" + "2. Validate created by\n" + "3. Validate created on date\n"
			+ "4. Validate the status code as 200.")
	public void addcurrencyTest() throws Exception {
		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String SHEET_NAME = "AddCurrency";
		Map<String, String> postData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
		Date date = new Date();
		apiUtil = new ApiUtil();
		System.out.println(date);
		String currentDate = date.toString();
		String originalDateStr = currentDate;

		// Generate random 5 letters currency code
		Random random = new Random();
		StringBuilder randomString = new StringBuilder(5);
		for (int i = 0; i < 5; i++) {
			int index = random.nextInt(CHARACTERS.length());
			randomString.append(CHARACTERS.charAt(index));
		}
		postData.put("CurrencyCode", randomString.toString());

		DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
		ZonedDateTime parsedDate = ZonedDateTime.parse(originalDateStr, inputFormatter);
		DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSXXX");
		String formattedDateStr = parsedDate.format(outputFormatter);
		System.out.println("Converted Date: " + formattedDateStr);
		postData.put("CreatedOn", formattedDateStr);
		Response addCurrencyResponse = apiUtil.addCurrencyWithAuth("/InventorySettings/Currency", postData);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH, "addCurrencyWithAuth",
				List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(addCurrencyResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for the addition of the currency
		String status = addCurrencyResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// 3. Validate the currency-related fields in the response
		String currencyCode = addCurrencyResponse.jsonPath().getString("Results.CurrencyCode");
		int createdBy = addCurrencyResponse.jsonPath().getInt("Results.CreatedBy");
		String createdOn = addCurrencyResponse.jsonPath().getString("Results.CreatedOn");
		boolean isActive = addCurrencyResponse.jsonPath().getBoolean("Results.IsActive");

		// Expected values from postData (assuming CreatedBy and IsActive are passed in
		// postData as Strings)
		String expectedCurrencyCode = postData.get("CurrencyCode");
		int expectedCreatedBy = Integer.parseInt(postData.get("CreatedBy"));
		boolean expectedIsActive = Boolean.parseBoolean(postData.get("IsActive"));

		// Validate CurrencyCode
		Assert.assertEquals(currencyCode, expectedCurrencyCode, "CurrencyCode does not match the expected value.");

		// Validate CreatedBy
		Assert.assertEquals(createdBy, expectedCreatedBy, "CreatedBy does not match the expected value.");

		// Validate CreatedOn
		Assert.assertNotNull(createdOn, "CreatedOn should not be null.");

		// Validate IsActive
		Assert.assertEquals(isActive, expectedIsActive, "IsActive does not match the expected value.");

		// Print the response from adding the currency
		System.out.println("Added Currency Response:");
		addCurrencyResponse.prettyPrint();
	}

	@Test(priority = 16, groups = { "PL1" }, description = "Precondition: Matching Patient must exist\n"
			+ "1. Validate that the appointment ID is not null.\n"
			+ "2. Send a GET request to find patient that has same phone number.\n"
			+ "3. Verify the response status code is 200.")
	public void findMatchingPatientTest() throws Exception {
		apiUtil = new ApiUtil();
		String SHEET_NAME = "MatchingPatient";
		Map<String, String> postData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		String firstName = postData.get("FirstName");
		String lastName = postData.get("LastName");
		String phoneNumber = postData.get("PhoneNumber");
		String age = postData.get("Age");
		String gender = postData.get("Gender");
		String isInsurance = postData.get("IsInsurance");
		String imisCode = postData.get("IMISCode");

		Response matchingPatientResponse = apiUtil.findMatchingPatientWithAuth("/Patient/MatchingPatients?FirstName="
				+ firstName + "&LastName=" + lastName + "&PhoneNumber=" + phoneNumber + "&Age=" + age + "&Gender="
				+ gender + "&IsInsurance=" + isInsurance + "&IMISCode=" + imisCode + "", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"findMatchingPatientWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(matchingPatientResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for Matching Patients Response
		String status = matchingPatientResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Validate the phone number is same
		String actualPhoneNumber = matchingPatientResponse.jsonPath().getString("Results[0].PhoneNumber");
		Assert.assertEquals(actualPhoneNumber, phoneNumber, "Phone number does not match");

		// Print the response from canceling the appointment
		System.out.println("Matching Patient Response:");
		matchingPatientResponse.prettyPrint();
	}

	@Test(priority = 17, groups = { "PL1" }, description = "1. Send a GET request to get all the registered patients\n"
			+ "2. Validate that all the patient IDs are unique.\n" + "3. Verify the response status code is 200.")
	public void getAllRegisteredPatientsTest() throws IOException {
		apiUtil = new ApiUtil();

		Response matchingPatientResponse = apiUtil
				.getRegisteredPatientsWithAuth("/Patient/SearchRegisteredPatient?search=", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"getRegisteredPatientsWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(matchingPatientResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for Registered Patients response
		String status = matchingPatientResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		List<Map<String, Object>> results = matchingPatientResponse.jsonPath().getList("Results");

		// Validate that all the patient IDs are unique
		Set<Integer> patientIds = results.stream().map(result -> (Integer) result.get("PatientId"))
				.collect(Collectors.toSet());

		Assert.assertEquals(patientIds.size(), results.size(), "Each patient should have a unique PatientID.");

		// Print the response from canceling the appointment
		System.out.println("Matching Patient Response:");
		matchingPatientResponse.prettyPrint();
	}

	@Test(priority = 18, groups = { "PL1" }, description = "1. Send a GET request to get all billing counters\n"
			+ "2. Validate that all the counter IDs are unique.\n" + "3. Verify the response status code is 200.")
	public void getAllBillingCounters() throws IOException {
		apiUtil = new ApiUtil();

		Response billingCounterResponse = apiUtil.getBillingCountersWithAuth("/billing/BillingCounters", null);

		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"getBillingCountersWithAuth", List.of("given", "then", "extract", "response"));

		System.out.println("---------------------------------------------" + isValidationSuccessful
				+ "------------------------------");

		Assert.assertEquals(billingCounterResponse.statusCode(), 200, "Status code should be 200 OK.");

		// Validate the response fields for Registered Patients response
		String status = billingCounterResponse.jsonPath().getString("Status");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		List<Map<String, Object>> results = billingCounterResponse.jsonPath().getList("Results");

		// Validate that all the patient IDs are unique
		Set<Integer> patientIds = results.stream().map(result -> (Integer) result.get("CounterId"))
				.collect(Collectors.toSet());

		Assert.assertEquals(patientIds.size(), results.size(), "Counter IDs should be unique.");

		// Print the response from canceling the appointment
		System.out.println("Billing Counters Response:");
		billingCounterResponse.prettyPrint();
	}

}
