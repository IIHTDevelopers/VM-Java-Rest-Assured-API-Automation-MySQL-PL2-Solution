package testcases;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import coreUtilities.utils.FileOperations;
import rest.ApiUtil;
import rest.CustomResponse;

public class RestAssured_TestCases_PL2 {

	FileOperations fileOperations = new FileOperations();

	private final String EXCEL_FILE_PATH = "src/main/resources/config.xlsx"; // Path to the Excel file
	private final String FILEPATH = "src/main/java/rest/ApiUtil.java";
	ApiUtil apiUtil;

	public static int appointmentId;

	@Test(priority = 1, groups = { "PL2" }, description = "Precondition: Create an appointment via the API\n"
			+ "1. Send POST request to create a new appointment with provided data\n"
			+ "2. Verify the response status code is 200 OK\n" + "3. Validate the response contains 'Status' as 'OK'\n"
			+ "4. Retrieve and validate the Appointment ID from the response")
	public void createAppointmentTest() throws Exception {
		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
		Map<String, String> postData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);

		// Construct the JSON payload as a string
		String requestBody = "{ " + "\"FirstName\": \"" + postData.get("FirstName") + "\", " + "\"LastName\": \""
				+ postData.get("LastName") + "\", " + "\"Gender\": \"" + postData.get("Gender") + "\", " + "\"Age\": \""
				+ postData.get("Age") + "\", " + "\"ContactNumber\": \"" + postData.get("ContactNumber") + "\", "
				+ "\"AppointmentDate\": \"" + postData.get("AppointmentDate") + "\", " + "\"AppointmentTime\": \""
				+ postData.get("AppointmentTime") + "\", " + "\"PerformerName\": \"" + postData.get("PerformerName")
				+ "\", " + "\"AppointmentType\": \"" + postData.get("AppointmentType") + "\", " + "\"DepartmentId\": "
				+ postData.get("DepartmentId") + " }";

		apiUtil = new ApiUtil();
		CustomResponse customResponse = apiUtil.createAppointmentWithAuth("/Appointment/AddAppointment", requestBody);

		// Validate the method's source code
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"createAppointmentWithAuth", List.of("given", "then", "extract", "response"));
		Assert.assertTrue(isValidationSuccessful,
				"createAppointmentWithAuth must be implemented using Rest Assured methods only.");

		// Validate response structure
		Assert.assertTrue(TestCodeValidator.validateResponseFields("createAppointmentWithAuth", customResponse),
				"Must have all required fields in the response.");

		// Validate the status code
		Assert.assertEquals(customResponse.getStatusCode(), 200, "Status code should be 200.");

		// Validate the top-level status field
		String status = customResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Validate the AppointmentId field
		Integer appointmentIdd = customResponse.getAppointmentId();
		appointmentId = appointmentIdd; 
		Assert.assertNotNull(appointmentIdd, "Appointment ID should not be null.");

		// Print the full response body for debugging
		System.out.println("Create Appointment Response:");
		customResponse.getResponse().prettyPrint();
	}

	@Test(priority = 2, groups = {
			"PL2" }, dependsOnMethods = "createAppointmentTest", description = "Precondition: An appointment must be created successfully.\n"
					+ "1. Validate that the appointment ID is not null.\n"
					+ "2. Send a PUT request to cancel the appointment using the appointment ID.\n"
					+ "3. Verify the response status code is 200.\n"
					+ "4. Validate the response indicates successful cancellation.")
	public void cancelAppointmentTest() throws IOException {
		apiUtil = new ApiUtil();

		// Ensure the appointment ID is set by the createAppointmentTest
		Assert.assertNotNull(appointmentId, "Appointment ID should be set by the createAppointmentTest.");

		// Call cancelAppointmentWithAuth to cancel the appointment
		CustomResponse cancelResponse = apiUtil.cancelAppointmentWithAuth(
				"/Appointment/AppointmentStatus?appointmentId=" + appointmentId + "&status=cancelled", null);
		
		// Validate method implementation (like Rest Assured methods used)
		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
				"cancelAppointmentWithAuth", List.of("given", "then", "extract", "response"));
		Assert.assertTrue(isValidationSuccessful,
				"cancelAppointmentWithAuth must be implemented using Rest Assured methods only.");

		// Validate response status code
		Assert.assertEquals(cancelResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Validate the top-level status field
		String status = cancelResponse.getStatus();
		System.out.println(cancelResponse.getStatus());
		System.out.println("cancelResponse.getStatus()--------------------");
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Validate the Results field for success message
		String resultMessage = cancelResponse.getResultMessage();
		Assert.assertEquals(resultMessage, "Appointment information updated successfully.",
				"Message should confirm the update.");

		// Print the full response for debugging
		System.out.println("Cancelled Appointment Response:");
		cancelResponse.getResponse().prettyPrint();
	}

	@Test(priority = 3, groups = {
			"PL2" }, description = "Precondition: Patients and Doctor must be created successfully.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successful display of all the users that contain the string in their name.")
	public void searchPatientTest() throws Exception {
		apiUtil = new ApiUtil();

		// Send request and get response
		CustomResponse searchedResponse = apiUtil.searchPatientWithAuth("/Patient/SearchRegisteredPatient?search=Test",
				null);

		// Validate response status code
		Assert.assertEquals(searchedResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
		String firstName = searchedResponse.getResponse().jsonPath().getString("Results[0].FirstName");
		String shortName = searchedResponse.getResponse().jsonPath().getString("Results[0].ShortName");
		String lastName = searchedResponse.getResponse().jsonPath().getString("Results[0].LastName");

		// Print the values to verify
		System.out.println("FirstName: " + firstName);
		System.out.println("ShortName: " + shortName);
		System.out.println("LastName: " + lastName);

		// Validate that 'firstName' and 'shortName' contain "Test"
		Assert.assertTrue(firstName.contains("Test"), "FirstName does not contain 'Test'");
		Assert.assertTrue(shortName.contains("Test"), "ShortName does not contain 'Test'");

		// Validate the 'Status' field
		String status = searchedResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Searched Patient Response:");
		searchedResponse.getResponse().prettyPrint();
	}

	@Test(priority = 4, groups = {
			"PL2" }, description = "Precondition: Appointments must be made between current date and 5 days before the current date.\n"
					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successful display of appointments along with patient Id and Appointment time.")
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
		CustomResponse updateResponse = apiUtil.bookingListWithAuthInRange("/Appointment/Appointments?FromDate="
				+ dateFiveDaysBeforeStr + "&ToDate=" + currentDateStr + "&performerId=" + performerId + "&status=new",
				null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(updateResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list and appointment dates
		List<Map<String, Object>> results = updateResponse.getListResults();
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
		String status = updateResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Searched appointment Response Within a Range:");
		updateResponse.getResponse().prettyPrint();
	}

	@Test(priority = 5, groups = {
			"PL2" }, description = "1. Send a GET request to fetch Main Store from the Pharmacy Settings.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response has an Id corresponding to the store along with the name and store description.")
	public void MainStoreTest() {
		apiUtil = new ApiUtil();

		// Send request and get response
		CustomResponse stockDetails = apiUtil.MainStoreDetailsWithAuth("/PharmacySettings/MainStore", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(stockDetails.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract 'Results' from the response
		Map<String, Object> results = stockDetails.getMapResults();
		System.out.println("Results: " + results);

		// Extract 'Name', 'StoreDescription', and 'StoreId'
		String Name = (String) results.get("Name");
		String storeDesc = (String) results.get("StoreDescription");
		Integer StoreId = (Integer) results.get("StoreId");

		// Assert that 'name', 'store description' and 'store Id' are not null
		Assert.assertNotNull(Name, "The Name is null and the store doesn't exist.");
		Assert.assertNotNull(storeDesc, "The store description is null and the store doesn't exist.");
		Assert.assertNotNull(StoreId, "The StoreId is null and the store doesn't exist.");

		// Validate the 'Status' field
		String status = stockDetails.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Fetched Main Store from the Pharmacy Settings:");
		stockDetails.getResponse().prettyPrint();
	}

	@Test(priority = 6, groups = {
			"PL2" }, description = "Precondition: Some Pharmacy Stores must be created already. \n"
					+ "1. Send a GET request to fetch whether we are able to fetch the pharmacy stores or not.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successful display of name of the store along with Store Id.")
	public void PharmacyStoreTest() {
		apiUtil = new ApiUtil();

		// Send request and get response
		CustomResponse pharmacyStoreResponse = apiUtil.PharmacyStoresWithAuth("/Dispensary/PharmacyStores", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(pharmacyStoreResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list
		List<Map<String, Object>> results = pharmacyStoreResponse.getListResults();
		System.out.println("Results: " + results);

		// Iterate over each result to print and verify the 'StoreId' and 'Name'
		for (Map<String, Object> result : results) {
			Integer storeId = (Integer) result.get("StoreId");
			String name = (String) result.get("Name");

			System.out.println("StoreId: " + storeId);
			System.out.println("Name: " + name);

			// Assert that 'StoreId' and 'Name' are not null
			Assert.assertNotNull(storeId, "The Store Id is null and the store doesn't exist.");
			Assert.assertNotNull(name, "The Name is null and the store doesn't exist.");
		}

		// Validate the 'Status' field
		String status = pharmacyStoreResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following are the Pharmacy Stores:");
		pharmacyStoreResponse.getResponse().prettyPrint();
	}

	@Test(priority = 7, groups = {
			"PL2" }, description = "Pre-conditions: Will require the counter Id and counterName to enter as a query parameter in the API. \n"
					+ "1. Send a PUT request to see whether we are able to activate the pharmacy counter.\n"
					+ "2. Verify the response status code is 200.\n"
					+ "3. Validate the response indicates successful display of counter Id and counterName.")
	public void ActivatePharmacyCountTest() throws Exception {
		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
		apiUtil = new ApiUtil();
		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
		String counterId = searchResult.get("CounterId");
		String counterName = searchResult.get("CounterName");

		System.out.println("The counter id from the sheet is: " + counterId);
		System.out.println("The counter name from the sheet is: " + counterName);

		// Send request and get response
		CustomResponse activationResponse = apiUtil.ActivatePharmCount(
				"/Security/ActivatePharmacyCounter?counterId=" + counterId + "&counterName=" + counterName, null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(activationResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract 'Results' from the response
		Map<String, Object> results = activationResponse.getMapResults();
		System.out.println("Results: " + results);

		// Extract 'CounterName' and 'CounterId'
		String counterNameResult = (String) results.get("CounterName");
		Integer counterIdResult = (Integer) results.get("CounterId");

		// Assert that 'CounterName' and 'CounterId' are not null
		Assert.assertNotNull(counterNameResult, "The Counter Name is null and the counter doesn't exist.");
		Assert.assertNotNull(counterIdResult, "The Counter Id is null and the counter doesn't exist.");

		// Validate the 'Status' field
		String status = activationResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("Activated the pharmacy counter, Response :");
		activationResponse.getResponse().prettyPrint();
	}

//	@Test(priority = 8, groups = {
//			"PL2" }, description = "1. Send a PUT request to fetch whether we are able to deactivate the pharmacy counter.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successfull display of status code as 200.")
//
//	public void DeactivatePharmCountTest() throws Exception {
//		apiUtil = new ApiUtil();
//		Response consumptionResponse = apiUtil.DeactivatePharmCount("/Security/DeactivatePharmacyCounter", null);
//
//		Assert.assertEquals(consumptionResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		String result = consumptionResponse.jsonPath().getString("Results");
//		System.out.println("Results: " + result);
//
//		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
//		String StatusCode = consumptionResponse.jsonPath().getString("Results.StatusCode");
//
//		// Assert that 'name, store description and store Id' is not null
//		Assert.assertTrue(StatusCode.equals("200"), "The status code is not 200 rather, " + StatusCode);
//
//		String status = consumptionResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Deactivated pharmacy counter: Response");
//		consumptionResponse.prettyPrint();
//	}
//
//	@Test(priority = 9, groups = {
//			"PL2" }, description = "1. Send a GET request to fetch a list of Appointment Applicable Departments.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successfull display of department name, department id, department code")
//
//	public void AppointApplicDeptTest() throws Exception {
//		apiUtil = new ApiUtil();
//		Response appointResponse = apiUtil.AppointApplicDept("/Master/AppointmentApplicableDepartments", null);
//
//		Assert.assertEquals(appointResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = appointResponse.jsonPath().getList("Results");
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			String DepartmentId = result.get("DepartmentId").toString();
//			String DepartmentName = result.get("DepartmentName").toString();
//			System.out.println("DepartmentId: " + DepartmentId);
//			System.out.println("DepartmentName: " + DepartmentName);
//			System.out.println("\n");
//
//			Assert.assertNotNull(DepartmentId, "The Department Id is null and the store doesn't exist.");
//			Assert.assertNotNull(DepartmentName, "The Department Name is null and the store doesn't exist.");
//		}
//
//		String status = appointResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Appointment Applicable Departments, Response:");
//		appointResponse.prettyPrint();
//	}
//
//	@Test(priority = 10, groups = {
//			"PL2" }, description = "1. Send a GET request to fetch a list of currently Admitted Patients Data.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successfull display of Patient Admission Id, Admitted Date but Discharged Date must be null")
//
//	public void AdmittedPatientsData() throws Exception {
//		apiUtil = new ApiUtil();
//		Response admittedPatientResponse = apiUtil
//				.admittedPatientData("/Admission/AdmittedPatientsData?admissionStatus=admitted", null);
//
//		Assert.assertEquals(admittedPatientResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = admittedPatientResponse.jsonPath().getList("Results");
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			String PatientId = result.get("PatientId").toString();
//			String AdmittedDate = result.get("AdmittedDate").toString();
//
//			System.out.println("PatientId: " + PatientId);
//			System.out.println("AdmittedDate: " + AdmittedDate);
//			System.out.println("\n");
//
//			Assert.assertNotNull(PatientId, "The Patient Id is null and the store doesn't exist.");
//			Assert.assertNotNull(AdmittedDate, "The Admitted Date is null and the store doesn't exist.");
//			// Verify that DischargedDate is null
//			Assert.assertNull(result.get("DischargedDate"), "DischargedDate should be null");
//
//		}
//
//		String status = admittedPatientResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Admitted Patients Data, Response:");
//		admittedPatientResponse.prettyPrint();
//	}
//
//	@Test(priority = 11, groups = { "PL2" }, description = "1. Send a GET request to fetch profile details.\n"
//			+ "2. Verify the response status code is 200.\n" + "3. Verify employee ID is not null. \n"
//			+ "3. Validate the response indicates successful display of account holder details.")
//
//	public void GetProfileDataByEmployeeId() throws Exception {
//		String SHEET_NAME = "ExpectedProfileDetails"; // Sheet name in the Excel file
//		Map<String, String> expectedProfileDetails = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//
//		String expectedEmployeeId = expectedProfileDetails.get("EmployeeId");
//		String expectedFirstName = expectedProfileDetails.get("FirstName");
//		String expectedlastName = expectedProfileDetails.get("LastName");
//		String expecteddob = expectedProfileDetails.get("DateOfBirth");
//		String expectedemailAddress = expectedProfileDetails.get("Email");
//		String expecteduserName = expectedProfileDetails.get("UserName");
//
//		apiUtil = new ApiUtil();
//		Response profileDetailsWithIdResponse = apiUtil
//				.getProfileDetails("/Employee/Profile?empId=" + expectedEmployeeId, null);
//
//		Assert.assertEquals(profileDetailsWithIdResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		String status = profileDetailsWithIdResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "The Status should be OK");
//
//		String EmployeeId = profileDetailsWithIdResponse.jsonPath().getString("Results.EmployeeId");
//		String actualFirstName = profileDetailsWithIdResponse.jsonPath().getString("Results.FirstName");
//		String actuallastName = profileDetailsWithIdResponse.jsonPath().getString("Results.LastName");
//		String actualdob = profileDetailsWithIdResponse.jsonPath().getString("Results.DateOfBirth");
//		String actualemailAddress = profileDetailsWithIdResponse.jsonPath().getString("Results.Email");
//		String actualuserName = profileDetailsWithIdResponse.jsonPath().getString("Results.UserName");
//
//		// Assert employee ID is not null
//		Assert.assertNotNull(EmployeeId, "The Employee Id is null.");
//
//		// Assert response with expected data from excel
//		Assert.assertEquals(EmployeeId, expectedEmployeeId, "The employee ID does not match with expected data");
//		Assert.assertEquals(actualFirstName, expectedFirstName, "The First Name does not match with expected data");
//		Assert.assertEquals(actuallastName, expectedlastName, "The Last Name does not match with expected data");
//		Assert.assertEquals(actualdob, expecteddob, "The Date of Birth does not match with expected data");
//		Assert.assertEquals(actualemailAddress, expectedemailAddress,
//				"The email address does not match with expected data");
//		Assert.assertEquals(actualuserName, expecteduserName, "The Username does not match with expected data");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the response of profile details, Response:");
//		profileDetailsWithIdResponse.prettyPrint();
//	}
//
//	@Test(priority = 12, groups = {
//			"PL2" }, description = "1. Send a GET request to add a new Department and fetch response details.\n"
//					+ "2. Verify the response status code is 200.\n" + "3. Verify Department code is not null. \n"
//					+ "3. Validate the response indicates successful display of department creation.")
//
//	public void addANewDepartment() throws Exception {
//		String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//		apiUtil = new ApiUtil();
//		// Generate random 5 letters currency code
//		Random random = new Random();
//		StringBuilder code = new StringBuilder(5);
//		for (int i = 0; i < 5; i++) {
//			int index = random.nextInt(CHARACTERS.length());
//			code.append(CHARACTERS.charAt(index));
//		}
//		String expectedDepartmentCode = code.toString();
//		String expectedDepartmentName = "Department " + expectedDepartmentCode;
//		Map<String, String> postData = new HashMap<>();
//		postData.put("DepartmentCode", expectedDepartmentCode); // replace "ABCD" with actual value if needed
//		postData.put("DepartmentName", expectedDepartmentName);
//		Response addANewDepartmentResponse = apiUtil.addDepartment("/Settings/Department", postData);
//
//		Assert.assertEquals(addANewDepartmentResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		String status = addANewDepartmentResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "The Status should be OK");
//
//		String actualDepartmentId = addANewDepartmentResponse.jsonPath().getString("Results.DepartmentId");
//		String actualDepartmentCode = addANewDepartmentResponse.jsonPath().getString("Results.DepartmentCode");
//		String actualDepartmentName = addANewDepartmentResponse.jsonPath().getString("Results.DepartmentName");
//
//		Assert.assertNotNull(actualDepartmentId, "The Employee Id is null.");
//		Assert.assertEquals(actualDepartmentCode, expectedDepartmentCode,
//				"The Department Code does not match with the expected data.");
//		Assert.assertEquals(actualDepartmentName, expectedDepartmentName,
//				"The Department Name does not match with the expected data.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the response after adding the department, Response:");
//		addANewDepartmentResponse.prettyPrint();
//	}
//
//	@Test(priority = 13, groups = { "PL2" }, description = "1. Send a GET request to get the list of departments.\n"
//			+ "2. Verify the response status code is 200.\n" + "3. Verify the department codes are unique.\n" + "")
//
//	public void GetDepartments() throws Exception {
//		apiUtil = new ApiUtil();
//		Response departmentsListResponse = apiUtil.getDepartmentsList("/Settings/Departments", null);
//
//		Assert.assertEquals(departmentsListResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = departmentsListResponse.jsonPath().getList("Results");
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			String DepartmentId = result.get("DepartmentId").toString();
//			String DepartmentName = result.get("DepartmentName").toString();
//			System.out.println("DepartmentId: " + DepartmentId);
//			System.out.println("DepartmentName: " + DepartmentName);
//			System.out.println("\n");
//
//			Assert.assertNotNull(DepartmentId, "The Department Id is null.");
//			Assert.assertNotNull(DepartmentName, "The Department Name is null.");
//		}
//
//		String status = departmentsListResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Departments, Response:");
//		departmentsListResponse.prettyPrint();
//	}
//
//	@Test(priority = 14, groups = { "PL2" }, description = "1. Send a PUT request to edit the department details.\n"
//			+ "2. Verify the response status code is 200.\n"
//			+ "3. Validate the response indicates successful display of department details with changes being present in the response.")
//
//	public void EditDepartment() throws Exception {
//		String SHEET_NAME = "EditDepartmentData"; // Sheet name in the Excel file
//		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//		String departmentCodeRes = searchResult.get("DepartmentCode");
//		String departmentNameRes = searchResult.get("DepartmentName");
//
//		System.out.println("The department Code Res from the sheet is: " + departmentCodeRes);
//		System.out.println("The department Name from the sheet is: " + departmentNameRes);
//		apiUtil = new ApiUtil();
//		Response editDepartmentResponse = apiUtil.editDepartmentDetails("/Settings/Department", searchResult);
//
//		Assert.assertEquals(editDepartmentResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		Map<String, Object> results = editDepartmentResponse.jsonPath().getMap("Results");
//		System.out.println("Results: " + results);
//
//		// Extract individual values from the result map
//		String DepartmentCode = (String) results.get("DepartmentCode");
//		String DepartmentName = (String) results.get("DepartmentName");
//
//		System.out.println("DepartmentCode From Response: " + DepartmentCode);
//		System.out.println("DepartmentName From Response: " + DepartmentName);
//		System.out.println("\n");
//
//		Assert.assertNotNull(DepartmentCode, "The DepartmentCode is null.");
//		Assert.assertNotNull(DepartmentName, "The Department Name is null.");
//
//		// verify that the department code and department name passed in the request are
//		// same as those fetched in the response.
//		Assert.assertEquals(DepartmentCode, departmentCodeRes);
//
//		Assert.assertEquals(DepartmentName, departmentNameRes);
//
//		String status = editDepartmentResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the Data of Department, Response:");
//		editDepartmentResponse.prettyPrint();
//	}
//
//	@Test(priority = 15, groups = { "PL2" }, description = "1. Send a GET request to get imaging types.\n"
//			+ "2. Verify the response status code is 200.\n")
//
//	public void GetImagingTypes() throws Exception {
//		apiUtil = new ApiUtil();
//		Response imagingTypesResponse = apiUtil.getImagingDataResponse("/RadiologySettings/ImagingTypes", null);
//
//		Assert.assertEquals(imagingTypesResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = imagingTypesResponse.jsonPath().getList("Results");
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			String ImagingTypeId = result.get("ImagingTypeId").toString();
//			String ImagingTypeName = result.get("ImagingTypeName").toString();
//
//			System.out.println("ImagingTypeId: " + ImagingTypeId);
//			System.out.println("ImagingTypeName: " + ImagingTypeName);
//			System.out.println("\n");
//
//			Assert.assertNotNull(ImagingTypeId, "The ImagingType Id is null and the store doesn't exist.");
//			Assert.assertNotNull(ImagingTypeName, "The Imaging Type Name is null and the store doesn't exist.");
//
//		}
//
//		String status = imagingTypesResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Imaging Types, Response:");
//		imagingTypesResponse.prettyPrint();
//	}
//
//	@Test(priority = 16, groups = { "PL2" }, description = "1. Send a GET request to get signatories by department.\n"
//			+ "2. Verify the response status code is 200.\n"
//			+ "3. Verify that the EmployeeId, FirstName, LastName are not null from the response validating that the data is present.")
//
//	public void GetSignatoriesByDepartment() throws Exception {
//
//		apiUtil = new ApiUtil();
//		Response signDetailsFromDeptResponse = apiUtil
//				.getsignatoriesDetails("/Master/Signatories?departmentName=radiology", null);
//
//		Assert.assertEquals(signDetailsFromDeptResponse.statusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = signDetailsFromDeptResponse.jsonPath().getList("Results");
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			String EmployeeId = result.get("EmployeeId").toString();
//			String FirstName = result.get("FirstName").toString();
//			String LastName = result.get("LastName").toString();
//
//			System.out.println("EmployeeId: " + EmployeeId);
//			System.out.println("FirstName: " + FirstName);
//			System.out.println("LastName: " + LastName);
//			System.out.println("\n");
//
//			Assert.assertNotNull(EmployeeId, "The EmployeeId is null");
//			Assert.assertNotNull(FirstName, "The FirstName is null");
//			Assert.assertNotNull(LastName, "The LastName is null");
//
//		}
//
//		String status = signDetailsFromDeptResponse.jsonPath().getString("Status");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following are the details of Signatories, Response:");
//		signDetailsFromDeptResponse.prettyPrint();
//	}
}
