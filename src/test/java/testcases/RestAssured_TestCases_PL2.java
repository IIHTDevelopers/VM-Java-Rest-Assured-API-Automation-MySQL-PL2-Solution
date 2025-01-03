package testcases;

import java.util.List;
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

//	@Test(priority = 1, groups = { "PL2" }, description = "Precondition: Create an appointment via the API\n"
//			+ "1. Send POST request to create a new appointment with provided data\n"
//			+ "2. Verify the response status code is 200 OK\n" + "3. Validate the response contains 'Status' as 'OK'\n"
//			+ "4. Retrieve and validate the Appointment ID from the response")
//	public void createAppointmentTest() throws Exception {
//		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
//		Map<String, String> postData = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//
//		// Construct the JSON payload as a string
//		String requestBody = "{ " + "\"FirstName\": \"" + postData.get("FirstName") + "\", " + "\"LastName\": \""
//				+ postData.get("LastName") + "\", " + "\"Gender\": \"" + postData.get("Gender") + "\", " + "\"Age\": \""
//				+ postData.get("Age") + "\", " + "\"ContactNumber\": \"" + postData.get("ContactNumber") + "\", "
//				+ "\"AppointmentDate\": \"" + postData.get("AppointmentDate") + "\", " + "\"AppointmentTime\": \""
//				+ postData.get("AppointmentTime") + "\", " + "\"PerformerName\": \"" + postData.get("PerformerName")
//				+ "\", " + "\"AppointmentType\": \"" + postData.get("AppointmentType") + "\", " + "\"DepartmentId\": "
//				+ postData.get("DepartmentId") + " }";
//
//		apiUtil = new ApiUtil();
//		CustomResponse customResponse = apiUtil.createAppointmentWithAuth("/Appointment/AddAppointment", requestBody);
//
//		// Validate the method's source code
//		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
//				"createAppointmentWithAuth", List.of("given", "then", "extract", "response"));
//		Assert.assertTrue(isValidationSuccessful,
//				"createAppointmentWithAuth must be implemented using Rest Assured methods only.");
//
//		// Validate response structure
//		Assert.assertTrue(TestCodeValidator.validateResponseFields("createAppointmentWithAuth", customResponse),
//				"Must have all required fields in the response.");
//
//		// Validate the status code
//		Assert.assertEquals(customResponse.getStatusCode(), 200, "Status code should be 200.");
//
//		// Validate the top-level status field
//		String status = customResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Validate the AppointmentId field
//		Integer appointmentIdd = customResponse.getAppointmentId();
//		appointmentId = appointmentIdd; 
//		Assert.assertNotNull(appointmentIdd, "Appointment ID should not be null.");
//
//		// Print the full response body for debugging
//		System.out.println("Create Appointment Response:");
//		customResponse.getResponse().prettyPrint();
//	}
//
//	@Test(priority = 2, groups = {
//			"PL2" }, dependsOnMethods = "createAppointmentTest", description = "Precondition: An appointment must be created successfully.\n"
//					+ "1. Validate that the appointment ID is not null.\n"
//					+ "2. Send a PUT request to cancel the appointment using the appointment ID.\n"
//					+ "3. Verify the response status code is 200.\n"
//					+ "4. Validate the response indicates successful cancellation.")
//	public void cancelAppointmentTest() throws IOException {
//		apiUtil = new ApiUtil();
//
//		// Ensure the appointment ID is set by the createAppointmentTest
//		Assert.assertNotNull(appointmentId, "Appointment ID should be set by the createAppointmentTest.");
//
//		// Call cancelAppointmentWithAuth to cancel the appointment
//		CustomResponse cancelResponse = apiUtil.cancelAppointmentWithAuth(
//				"/Appointment/AppointmentStatus?appointmentId=" + appointmentId + "&status=cancelled", null);
//		
//		// Validate method implementation (like Rest Assured methods used)
//		boolean isValidationSuccessful = TestCodeValidator.validateTestMethodFromFile(FILEPATH,
//				"cancelAppointmentWithAuth", List.of("given", "then", "extract", "response"));
//		Assert.assertTrue(isValidationSuccessful,
//				"cancelAppointmentWithAuth must be implemented using Rest Assured methods only.");
//
//		// Validate response status code
//		Assert.assertEquals(cancelResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Validate the top-level status field
//		String status = cancelResponse.getStatus();
//		System.out.println(cancelResponse.getStatus());
//		System.out.println("cancelResponse.getStatus()--------------------");
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Validate the Results field for success message
//		String resultMessage = cancelResponse.getResultMessage();
//		Assert.assertEquals(resultMessage, "Appointment information updated successfully.",
//				"Message should confirm the update.");
//
//		// Print the full response for debugging
//		System.out.println("Cancelled Appointment Response:");
//		cancelResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 3, groups = {
//			"PL2" }, description = "Precondition: Patients and Doctor must be created successfully.\n"
//					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of all the users that contain the string in their name.")
//	public void searchPatientTest() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse searchedResponse = apiUtil.searchPatientWithAuth("/Patient/SearchRegisteredPatient?search=Test",
//				null);
//
//		// Validate response status code
//		Assert.assertEquals(searchedResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'FirstName' and 'ShortName' from the first item in 'Results'
//		String firstName = searchedResponse.getResponse().jsonPath().getString("Results[0].FirstName");
//		String shortName = searchedResponse.getResponse().jsonPath().getString("Results[0].ShortName");
//		String lastName = searchedResponse.getResponse().jsonPath().getString("Results[0].LastName");
//
//		// Print the values to verify
//		System.out.println("FirstName: " + firstName);
//		System.out.println("ShortName: " + shortName);
//		System.out.println("LastName: " + lastName);
//
//		// Validate that 'firstName' and 'shortName' contain "Test"
//		Assert.assertTrue(firstName.contains("Test"), "FirstName does not contain 'Test'");
//		Assert.assertTrue(shortName.contains("Test"), "ShortName does not contain 'Test'");
//
//		// Validate the 'Status' field
//		String status = searchedResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Searched Patient Response:");
//		searchedResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 4, groups = {
//			"PL2" }, description = "Precondition: Appointments must be made between current date and 5 days before the current date.\n"
//					+ "1. Send a GET request to fetch whether an appointment for the same time is created for the same doctor.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of appointments along with patient Id and Appointment time.")
//	public void BookingListTest() throws Exception {
//		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
//		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//		apiUtil = new ApiUtil();
//
//		// Set date range
//		LocalDate currentDate = LocalDate.now();
//		LocalDate dateFiveDaysBefore = currentDate.minusDays(5);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//
//		// Format dates as strings
//		String currentDateStr = currentDate.format(formatter);
//		String dateFiveDaysBeforeStr = dateFiveDaysBefore.format(formatter);
//		String performerId = searchResult.get("performerId");
//
//		// Send request and get response
//		CustomResponse updateResponse = apiUtil.bookingListWithAuthInRange("/Appointment/Appointments?FromDate="
//				+ dateFiveDaysBeforeStr + "&ToDate=" + currentDateStr + "&performerId=" + performerId + "&status=new",
//				null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(updateResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list and appointment dates
//		List<Map<String, Object>> results = updateResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		// Iterate over each result to print and verify the 'AppointmentDate'
//		for (Map<String, Object> result : results) {
//			String appointmentDateStr = result.get("AppointmentDate").toString().substring(0, 10); // Extract date
//																									// portion only
//			System.out.println("Appointment Date: " + appointmentDateStr);
//
//			// Parse the 'AppointmentDate' to LocalDate for comparison
//			LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);
//
//			// Assert that 'AppointmentDate' is within the specified range
//			Assert.assertTrue(!appointmentDate.isBefore(dateFiveDaysBefore) && !appointmentDate.isAfter(currentDate),
//					"AppointmentDate " + appointmentDate + " is not within the expected range: " + dateFiveDaysBeforeStr
//							+ " to " + currentDateStr);
//		}
//
//		// Validate the 'Status' field
//		String status = updateResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Searched appointment Response Within a Range:");
//		updateResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 5, groups = {
//			"PL2" }, description = "1. Send a GET request to fetch Main Store from the Pharmacy Settings.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response has an Id corresponding to the store along with the name and store description.")
//	public void MainStoreTest() {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse stockDetails = apiUtil.MainStoreDetailsWithAuth("/PharmacySettings/MainStore", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(stockDetails.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'Results' from the response
//		Map<String, Object> results = stockDetails.getMapResults();
//		System.out.println("Results: " + results);
//
//		// Extract 'Name', 'StoreDescription', and 'StoreId'
//		String Name = (String) results.get("Name");
//		String storeDesc = (String) results.get("StoreDescription");
//		Integer StoreId = (Integer) results.get("StoreId");
//
//		// Assert that 'name', 'store description' and 'store Id' are not null
//		Assert.assertNotNull(Name, "The Name is null and the store doesn't exist.");
//		Assert.assertNotNull(storeDesc, "The store description is null and the store doesn't exist.");
//		Assert.assertNotNull(StoreId, "The StoreId is null and the store doesn't exist.");
//
//		// Validate the 'Status' field
//		String status = stockDetails.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Fetched Main Store from the Pharmacy Settings:");
//		stockDetails.getResponse().prettyPrint();
//	}

//	@Test(priority = 6, groups = {
//			"PL2" }, description = "Precondition: Some Pharmacy Stores must be created already. \n"
//					+ "1. Send a GET request to fetch whether we are able to fetch the pharmacy stores or not.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of name of the store along with Store Id.")
//	public void PharmacyStoreTest() {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse pharmacyStoreResponse = apiUtil.PharmacyStoresWithAuth("/Dispensary/PharmacyStores", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(pharmacyStoreResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list
//		List<Map<String, Object>> results = pharmacyStoreResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		// Iterate over each result to print and verify the 'StoreId' and 'Name'
//		for (Map<String, Object> result : results) {
//			Integer storeId = (Integer) result.get("StoreId");
//			String name = (String) result.get("Name");
//
//			System.out.println("StoreId: " + storeId);
//			System.out.println("Name: " + name);
//
//			// Assert that 'StoreId' and 'Name' are not null
//			Assert.assertNotNull(storeId, "The Store Id is null and the store doesn't exist.");
//			Assert.assertNotNull(name, "The Name is null and the store doesn't exist.");
//		}
//
//		// Validate the 'Status' field
//		String status = pharmacyStoreResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following are the Pharmacy Stores:");
//		pharmacyStoreResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 7, groups = {
//			"PL2" }, description = "Pre-conditions: Will require the counter Id and counterName to enter as a query parameter in the API. \n"
//					+ "1. Send a PUT request to see whether we are able to activate the pharmacy counter.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of counter Id and counterName.")
//	public void ActivatePharmacyCountTest() throws Exception {
//		String SHEET_NAME = "AddAppointmentData"; // Sheet name in the Excel file
//		apiUtil = new ApiUtil();
//		Map<String, String> searchResult = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//		String counterId = searchResult.get("CounterId");
//		String counterName = searchResult.get("CounterName");
//
//		System.out.println("The counter id from the sheet is: " + counterId);
//		System.out.println("The counter name from the sheet is: " + counterName);
//
//		// Send request and get response
//		CustomResponse activationResponse = apiUtil.ActivatePharmCount(
//				"/Security/ActivatePharmacyCounter?counterId=" + counterId + "&counterName=" + counterName, null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(activationResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'Results' from the response
//		Map<String, Object> results = activationResponse.getMapResults();
//		System.out.println("Results: " + results);
//
//		// Extract 'CounterName' and 'CounterId'
//		String counterNameResult = (String) results.get("CounterName");
//		Integer counterIdResult = (Integer) results.get("CounterId");
//
//		// Assert that 'CounterName' and 'CounterId' are not null
//		Assert.assertNotNull(counterNameResult, "The Counter Name is null and the counter doesn't exist.");
//		Assert.assertNotNull(counterIdResult, "The Counter Id is null and the counter doesn't exist.");
//
//		// Validate the 'Status' field
//		String status = activationResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Activated the pharmacy counter, Response :");
//		activationResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 8, groups = {
//			"PL2" }, description = "1. Send a PUT request to fetch whether we are able to deactivate the pharmacy counter.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of status code as 200.")
//	public void DeactivatePharmCountTest() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse consumptionResponse = apiUtil.DeactivatePharmCount("/Security/DeactivatePharmacyCounter", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(consumptionResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'Results' from the response
//		Map<String, Object> results = consumptionResponse.getMapResults();
//		System.out.println("Results: " + results);
//
//		// Extract 'StatusCode' from 'Results'
//		Integer statusCode = (Integer) results.get("StatusCode");
//		System.out.println("statusCode");
//		System.out.println(statusCode);
//
//		// Assert that 'StatusCode' is 200
//		Assert.assertEquals(statusCode, 200, "The status code is not 200, rather " + statusCode);
//
//		// Validate the 'Status' field
//		String status = consumptionResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("Deactivated pharmacy counter: Response");
//		consumptionResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 9, groups = {
//			"PL2" }, description = "1. Send a GET request to fetch a list of Appointment Applicable Departments.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of department name, department id, department code")
//	public void AppointApplicDeptTest() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse appointResponse = apiUtil.AppointApplicDept("/Master/AppointmentApplicableDepartments", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(appointResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list
//		List<Map<String, Object>> results = appointResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		// Iterate over each department to validate 'DepartmentId' and 'DepartmentName'
//		for (Map<String, Object> result : results) {
//			Integer departmentId = (Integer) result.get("DepartmentId");
//			String departmentName = (String) result.get("DepartmentName");
//
//			System.out.println("DepartmentId: " + departmentId);
//			System.out.println("DepartmentName: " + departmentName);
//			System.out.println("\n");
//
//			// Assert that 'DepartmentId' and 'DepartmentName' are not null
//			Assert.assertNotNull(departmentId, "The Department Id is null and the department doesn't exist.");
//			Assert.assertNotNull(departmentName, "The Department Name is null and the department doesn't exist.");
//		}
//
//		// Validate the 'Status' field
//		String status = appointResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Appointment Applicable Departments, Response:");
//		appointResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 10, groups = {
//			"PL2" }, description = "1. Send a GET request to fetch a list of currently Admitted Patients Data.\n"
//					+ "2. Verify the response status code is 200.\n"
//					+ "3. Validate the response indicates successful display of Patient Admission Id, Admitted Date but Discharged Date must be null")
//	public void AdmittedPatientsData() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse admittedPatientResponse = apiUtil
//				.admittedPatientData("/Admission/AdmittedPatientsData?admissionStatus=admitted", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(admittedPatientResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list
//		List<Map<String, Object>> results = admittedPatientResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		// Iterate over each result to validate 'PatientId', 'AdmittedDate' and
//		// 'DischargedDate'
//		for (Map<String, Object> result : results) {
//			Integer patientId = (Integer) result.get("PatientId");
//			String admittedDate = (String) result.get("AdmittedDate");
//
//			System.out.println("PatientId: " + patientId);
//			System.out.println("AdmittedDate: " + admittedDate);
//			System.out.println("\n");
//
//			// Assert that 'PatientId' and 'AdmittedDate' are not null
//			Assert.assertNotNull(patientId, "The Patient Id is null and the patient doesn't exist.");
//			Assert.assertNotNull(admittedDate, "The Admitted Date is null and the patient doesn't exist.");
//
//			// Verify that DischargedDate is null
//			Assert.assertNull(result.get("DischargedDate"), "DischargedDate should be null for admitted patients.");
//		}
//
//		// Validate the 'Status' field
//		String status = admittedPatientResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Admitted Patients Data, Response:");
//		admittedPatientResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 11, groups = { "PL2" }, description = "1. Send a GET request to fetch profile details.\n"
//			+ "2. Verify the response status code is 200.\n" + "3. Verify employee ID is not null. \n"
//			+ "3. Validate the response indicates successful display of account holder details.")
//	public void GetProfileDataByEmployeeId() throws Exception {
//		String SHEET_NAME = "ExpectedProfileDetails"; // Sheet name in the Excel file
//		Map<String, String> expectedProfileDetails = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//
//		String expectedEmployeeId = expectedProfileDetails.get("EmployeeId");
//		String expectedFirstName = expectedProfileDetails.get("FirstName");
//		String expectedLastName = expectedProfileDetails.get("LastName");
//		String expectedDob = expectedProfileDetails.get("DateOfBirth");
//		String expectedEmailAddress = expectedProfileDetails.get("Email");
//		String expectedUserName = expectedProfileDetails.get("UserName");
//
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse profileDetailsWithIdResponse = apiUtil
//				.getProfileDetails("/Employee/Profile?empId=" + expectedEmployeeId, null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(profileDetailsWithIdResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'Results' from the response
//		Map<String, Object> results = profileDetailsWithIdResponse.getMapResults();
//		System.out.println("Results: " + results);
//
//		// Extract fields from 'Results'
//		Integer employeeId = (Integer) results.get("EmployeeId");
//		String actualFirstName = (String) results.get("FirstName");
//		String actualLastName = (String) results.get("LastName");
//		String actualDob = (String) results.get("DateOfBirth");
//		String actualEmailAddress = (String) results.get("Email");
//		String actualUserName = (String) results.get("UserName");
//
//		// Assert employee ID is not null
//		Assert.assertNotNull(employeeId, "The Employee Id is null.");
//
//		// Assert response with expected data from Excel
//		Assert.assertEquals(actualFirstName, expectedFirstName, "The First Name does not match with expected data.");
//		Assert.assertEquals(actualLastName, expectedLastName, "The Last Name does not match with expected data.");
//		Assert.assertEquals(actualDob, expectedDob, "The Date of Birth does not match with expected data.");
//		Assert.assertEquals(actualEmailAddress, expectedEmailAddress,
//				"The email address does not match with expected data.");
//		Assert.assertEquals(actualUserName, expectedUserName, "The Username does not match with expected data.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the response of profile details, Response:");
//		profileDetailsWithIdResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 12, groups = { "PL2" }, description = "1. Send a GET request to add a new Department and fetch response details.\n"
//	        + "2. Verify the response status code is 200.\n" + "3. Verify Department code is not null. \n"
//	        + "3. Validate the response indicates successful display of department creation.")
//	public void addANewDepartment() throws Exception {
//	    String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
//	    apiUtil = new ApiUtil();
//	    
//	    // Generate random 5 letters department code
//	    Random random = new Random();
//	    StringBuilder code = new StringBuilder(5);
//	    for (int i = 0; i < 5; i++) {
//	        int index = random.nextInt(CHARACTERS.length());
//	        code.append(CHARACTERS.charAt(index));
//	    }
//	    String expectedDepartmentCode = code.toString();
//	    String expectedDepartmentName = "Department " + expectedDepartmentCode;
//	    
//	    Map<String, String> postData = new HashMap<>();
//	    postData.put("DepartmentCode", expectedDepartmentCode);
//	    postData.put("DepartmentName", expectedDepartmentName);
//
//	    // Send request and get response
//	    CustomResponse addANewDepartmentResponse = apiUtil.addDepartment("/Settings/Department", postData);
//
//	    // Assert that the status code is 200 OK
//	    Assert.assertEquals(addANewDepartmentResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//	    // Extract 'Results' from the response
//	    Map<String, Object> results = addANewDepartmentResponse.getMapResults();
//	    System.out.println("Results: " + results);
//
//	    // Extract fields from 'Results'
//	    Integer actualDepartmentId = (Integer) results.get("DepartmentId");
//	    String actualDepartmentCode = (String) results.get("DepartmentCode");
//	    String actualDepartmentName = (String) results.get("DepartmentName");
//
//	    // Assert that 'DepartmentId' is not null
//	    Assert.assertNotNull(actualDepartmentId, "The Department Id is null.");
//	    // Assert that the 'DepartmentCode' matches the expected code
//	    Assert.assertEquals(actualDepartmentCode, expectedDepartmentCode, "The Department Code does not match with the expected data.");
//	    // Assert that the 'DepartmentName' matches the expected name
//	    Assert.assertEquals(actualDepartmentName, expectedDepartmentName, "The Department Name does not match with the expected data.");
//
//	    // Print the full response for further verification if needed
//	    System.out.println("The following is the response after adding the department, Response:");
//	    addANewDepartmentResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 13, groups = { "PL2" }, description = "1. Send a GET request to get the list of departments.\n"
//			+ "2. Verify the response status code is 200.\n" + "3. Verify the department codes are unique.\n")
//	public void GetDepartments() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse departmentsListResponse = apiUtil.getDepartmentsList("/Settings/Departments", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(departmentsListResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list
//		List<Map<String, Object>> results = departmentsListResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		Set<String> departmentCodes = new HashSet<>(); // To check for uniqueness of department codes
//
//		for (Map<String, Object> result : results) {
//			Integer departmentId = (Integer) result.get("DepartmentId");
//			String departmentName = (String) result.get("DepartmentName");
//			String departmentCode = (String) result.get("DepartmentCode");
//
//			System.out.println("DepartmentId: " + departmentId);
//			System.out.println("DepartmentName: " + departmentName);
//			System.out.println("DepartmentCode: " + departmentCode);
//			System.out.println("\n");
//
//			// Assert that 'DepartmentId' and 'DepartmentName' are not null
//			Assert.assertNotNull(departmentId, "The Department Id is null.");
//			Assert.assertNotNull(departmentName, "The Department Name is null.");
//		}
//
//		// Validate the 'Status' field
//		String status = departmentsListResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Departments, Response:");
//		departmentsListResponse.getResponse().prettyPrint();
//	}
//
//	@Test(priority = 14, groups = { "PL2" }, description = "1. Send a PUT request to edit the department details.\n"
//			+ "2. Verify the response status code is 200.\n"
//			+ "3. Validate the response indicates successful display of department details with changes being present in the response.")
//	public void EditDepartment() throws Exception {
//		String SHEET_NAME = "EditDepartmentData"; // Sheet name in the Excel file
//		Map<String, String> body = fileOperations.readExcelPOI(EXCEL_FILE_PATH, SHEET_NAME);
//		String departmentCodeRes = body.get("DepartmentCode");
//		String departmentNameRes = body.get("DepartmentName");
//
//		System.out.println("The department Code Res from the sheet is: " + departmentCodeRes);
//		System.out.println("The department Name from the sheet is: " + departmentNameRes);
//
//		// Retrieve values from the Map
//		String departmentId = body.get("DepartmentId");
//		String departmentCode = body.get("DepartmentCode");
//		String departmentName = body.get("DepartmentName");
//		String description = body.get("Description");
//		String noticeText = body.get("NoticeText");
//		String departmentHead = body.get("DepartmentHead");
//		boolean isActive = Boolean.parseBoolean(body.get("IsActive"));
//		boolean isAppointmentApplicable = Boolean.parseBoolean(body.get("IsAppointmentApplicable"));
//		String createdBy = body.get("CreatedBy");
//		String modifiedBy = body.get("ModifiedBy");
//		String modifiedOn = body.get("ModifiedOn");
//		String parentDepartmentId = body.get("ParentDepartmentId");
//		String parentDepartmentName = body.get("ParentDepartmentName");
//		String roomNumber = body.get("RoomNumber");
//		String serviceItemsList = body.get("ServiceItemsList"); // assuming this is serialized as a string
//		boolean isZeroPriceAllowed = Boolean.parseBoolean(body.get("IsZeroPriceAllowed"));
//		String opdNewPatientServiceItemId = body.get("OpdNewPatientServiceItemId");
//		String opdOldPatientServiceItemId = body.get("OpdOldPatientServiceItemId");
//		String followupServiceItemId = body.get("FollowupServiceItemId");
//
//		// Handle potential null values for integer fields
//		Integer departmentHeadInt = null;
//		if (departmentHead != null && !departmentHead.equals("null")) {
//			try {
//				departmentHeadInt = Integer.parseInt(departmentHead);
//			} catch (NumberFormatException e) {
//				System.out.println("Error parsing DepartmentHead: " + e.getMessage());
//			}
//		}
//
//		// Construct the JSON payload as a string
//		String requestBody = "{ " + "\"DepartmentId\": " + departmentId + ", " + "\"DepartmentCode\": \""
//				+ departmentCode + "\", " + "\"DepartmentName\": \"" + departmentName + "\", " + "\"Description\": "
//				+ (description != null ? "\"" + description + "\"" : null) + ", " + "\"NoticeText\": "
//				+ (noticeText != null ? "\"" + noticeText + "\"" : null) + ", " + "\"DepartmentHead\": "
//				+ (departmentHeadInt != null ? departmentHeadInt : "null") + ", " + "\"IsActive\": " + isActive + ", "
//				+ "\"IsAppointmentApplicable\": " + isAppointmentApplicable + ", " + "\"CreatedBy\": " + createdBy
//				+ ", " + "\"ModifiedBy\": " + modifiedBy + ", " + "\"ModifiedOn\": "
//				+ (modifiedOn != null ? modifiedOn : "null") + ", " + "\"ParentDepartmentId\": "
//				+ (parentDepartmentId != null ? parentDepartmentId : null) + ", " + "\"ParentDepartmentName\": "
//				+ (parentDepartmentName != null ? "\"" + parentDepartmentName + "\"" : null) + ", " + "\"RoomNumber\": "
//				+ (roomNumber != null ? "\"" + roomNumber + "\"" : null) + ", " + "\"ServiceItemsList\": "
//				+ (serviceItemsList != null ? serviceItemsList : "null") + ", " + "\"IsZeroPriceAllowed\": "
//				+ isZeroPriceAllowed + ", " + "\"OpdNewPatientServiceItemId\": "
//				+ (opdNewPatientServiceItemId != null ? opdNewPatientServiceItemId : "null") + ", "
//				+ "\"OpdOldPatientServiceItemId\": "
//				+ (opdOldPatientServiceItemId != null ? opdOldPatientServiceItemId : "null") + ", "
//				+ "\"FollowupServiceItemId\": " + (followupServiceItemId != null ? followupServiceItemId : "null")
//				+ " }";
//
//		apiUtil = new ApiUtil();
//		// Send request and get response
//		CustomResponse editDepartmentResponse = apiUtil.editDepartmentDetails("/Settings/Department", requestBody);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(editDepartmentResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract 'Results' from the response
//		Map<String, Object> results = editDepartmentResponse.getMapResults();
//		System.out.println("Results: " + results);
//
//		// Extract individual values from the result map
//		String departmentCode14 = (String) results.get("DepartmentCode");
//		String departmentName14 = (String) results.get("DepartmentName");
//
//		System.out.println("DepartmentCode From Response: " + departmentCode14);
//		System.out.println("DepartmentName From Response: " + departmentName14);
//		System.out.println("\n");
//
//		// Assert that 'DepartmentCode' and 'DepartmentName' are not null
//		Assert.assertNotNull(departmentCode14, "The DepartmentCode is null.");
//		Assert.assertNotNull(departmentName14, "The Department Name is null.");
//
//		// Verify that the department code and department name passed in the request are
//		// same as those fetched in the response.
//		Assert.assertEquals(departmentCode14, departmentCodeRes);
//		Assert.assertEquals(departmentName14, departmentNameRes);
//
//		String status = editDepartmentResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the Data of Department, Response:");
//		editDepartmentResponse.getResponse().prettyPrint();
//	}

//	@Test(priority = 15, groups = { "PL2" }, description = "1. Send a GET request to get imaging types.\n"
//			+ "2. Verify the response status code is 200.\n")
//	public void GetImagingTypes() throws Exception {
//		apiUtil = new ApiUtil();
//
//		// Send request and get response
//		CustomResponse imagingTypesResponse = apiUtil.getImagingDataResponse("/RadiologySettings/ImagingTypes", null);
//
//		// Assert that the status code is 200 OK
//		Assert.assertEquals(imagingTypesResponse.getStatusCode(), 200, "Status code should be 200 OK.");
//
//		// Extract and print the 'Results' list
//		List<Map<String, Object>> results = imagingTypesResponse.getListResults();
//		System.out.println("Results: " + results);
//
//		for (Map<String, Object> result : results) {
//			Integer imagingTypeId = (Integer) result.get("ImagingTypeId");
//			String imagingTypeName = (String) result.get("ImagingTypeName");
//
//			System.out.println("ImagingTypeId: " + imagingTypeId);
//			System.out.println("ImagingTypeName: " + imagingTypeName);
//			System.out.println("\n");
//
//			// Assert that 'ImagingTypeId' and 'ImagingTypeName' are not null
//			Assert.assertNotNull(imagingTypeId, "The ImagingType Id is null.");
//			Assert.assertNotNull(imagingTypeName, "The Imaging Type Name is null.");
//		}
//
//		// Validate the 'Status' field
//		String status = imagingTypesResponse.getStatus();
//		Assert.assertEquals(status, "OK", "Status should be OK.");
//
//		// Print the full response for further verification if needed
//		System.out.println("The following is the list of Imaging Types, Response:");
//		imagingTypesResponse.getResponse().prettyPrint();
//	}

	@Test(priority = 16, groups = { "PL2" }, description = "1. Send a GET request to get signatories by department.\n"
			+ "2. Verify the response status code is 200.\n"
			+ "3. Verify that the EmployeeId, FirstName, LastName are not null from the response validating that the data is present.")
	public void GetSignatoriesByDepartment() throws Exception {
		apiUtil = new ApiUtil();

		// Send request and get response
		CustomResponse signDetailsFromDeptResponse = apiUtil
				.getsignatoriesDetails("/Master/Signatories?departmentName=radiology", null);

		// Assert that the status code is 200 OK
		Assert.assertEquals(signDetailsFromDeptResponse.getStatusCode(), 200, "Status code should be 200 OK.");

		// Extract and print the 'Results' list
		List<Map<String, Object>> results = signDetailsFromDeptResponse.getListResults();
		System.out.println("Results: " + results);

		for (Map<String, Object> result : results) {
			Integer employeeId = (Integer) result.get("EmployeeId");
			String firstName = (String) result.get("FirstName");
			String lastName = (String) result.get("LastName");

			System.out.println("EmployeeId: " + employeeId);
			System.out.println("FirstName: " + firstName);
			System.out.println("LastName: " + lastName);
			System.out.println("\n");

			// Assert that 'EmployeeId', 'FirstName', and 'LastName' are not null
			Assert.assertNotNull(employeeId, "The EmployeeId is null");
			Assert.assertNotNull(firstName, "The FirstName is null");
			Assert.assertNotNull(lastName, "The LastName is null");
		}

		// Validate the 'Status' field
		String status = signDetailsFromDeptResponse.getStatus();
		Assert.assertEquals(status, "OK", "Status should be OK.");

		// Print the full response for further verification if needed
		System.out.println("The following are the details of Signatories, Response:");
		signDetailsFromDeptResponse.getResponse().prettyPrint();
	}

}
