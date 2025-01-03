package rest;

import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class ApiUtil {

	private static final String BASE_URL = "https://healthapp.yaksha.com/api";

	/**
	 * @Test1 This method creates a new appointment with authorization.
	 * 
	 * @param endpoint - The API endpoint to which the request is sent.
	 * @param body     - A JSON string containing the appointment details.
	 * @description This method sends a POST request to the specified endpoint with
	 *              the authorization header and the provided JSON payload, and
	 *              returns the response.
	 * @return CustomResponse - The API response includes HTTP status code, status
	 *         message, and appointment details (AppointmentId, etc.).
	 */
	public CustomResponse createAppointmentWithAuth(String endpoint, String body) {
		// Send the POST request
		Response response = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json").body(body).post(BASE_URL + endpoint).then().extract()
				.response();

		// Extract required data from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Integer appointmentId = response.jsonPath().getInt("Results.AppointmentId");

		// Return a CustomResponse object
		return new CustomResponse(response, statusCode, status, appointmentId);
	}

	/**
	 * @Test2 This method cancels an existing appointment with authorization.
	 * 
	 * @param endpoint - The API endpoint to which the request is sent for canceling
	 *                 the appointment.
	 * @param body     - An optional object representing the request body. This
	 *                 parameter can be null since the cancelation does not require
	 *                 a body payload.
	 * @description This method builds a PUT request with the authorization header
	 *              and specified endpoint. If a body is provided, it includes that
	 *              in the request; otherwise, it sends the request without a body.
	 * @return CustomResponse - The response from the API after attempting to cancel
	 *         the appointment, including status and result details.
	 */
	public CustomResponse cancelAppointmentWithAuth(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the PUT request
		Response response = request.put(BASE_URL + endpoint).then().extract().response();

		System.out.println("URL");
		System.out.println(BASE_URL + endpoint);
		System.out.println(response.prettyPrint());

		// Extract the necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		String resultMessage = response.jsonPath().getString("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, resultMessage);
	}

	/**
	 * @Test3 This method searches for a patient using specified query parameters.
	 *
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @description This method sends a GET request to the specified endpoint with
	 *              the necessary authorization header and query parameters to
	 *              search for a patient in the system. The API returns details of
	 *              patients matching the search criteria, including fields like
	 *              `PatientId`, `ShortName`, `FirstName`, `LastName`, `Age`, and
	 *              others.
	 *
	 * @return CustomResponse - The API's response after attempting to search for
	 *         patients, which includes the HTTP status code, status message, and
	 *         the list of matching patients in the "Results" field.
	 */
	public CustomResponse searchPatientWithAuth(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract the necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test4 This method retrieves a list of appointments for a specified performer
	 *        within a given date range.
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @description This method sends a GET request to retrieve all appointments for
	 *              a specified performer between `FromDate` and `ToDate`. The
	 *              request includes query parameters for date range and performer
	 *              ID, with necessary authorization headers. The API returns
	 *              details of matching appointments, including fields like
	 *              `AppointmentId`, `PatientId`, `FullName`, `AppointmentDate`,
	 *              `AppointmentTime`, `AppointmentStatus`, and other relevant
	 *              details.
	 *
	 * @return CustomResponse - The API's response, which includes the HTTP status
	 *         code, a status message, and a list of appointments in the "Results"
	 *         field, each containing appointment and patient details.
	 */
	public CustomResponse bookingListWithAuthInRange(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test5 This method retrieves details of the main store in the pharmacy
	 *        settings.
	 *
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @description This method sends a GET request to the specified endpoint with
	 *              the necessary authorization header to fetch details of the main
	 *              store in the pharmacy settings. The API response provides store
	 *              details, including `Name`, `StoreDescription`, and `StoreId`.
	 *
	 *              The method validates that: 1. The API response status code is
	 *              200 (OK). 2. Essential fields `Name`, `StoreDescription`, and
	 *              `StoreId` are not null, ensuring the store details are
	 *              populated. 3. The `Status` field in the response is "OK",
	 *              confirming a successful response.
	 *
	 * @return CustomResponse - The API's response after attempting to retrieve main
	 *         store details, including status and store details in the "Results"
	 *         field.
	 */
	public CustomResponse MainStoreDetailsWithAuth(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test6 This method retrieves a list of pharmacy stores and verifies the
	 *        details of each store.
	 *
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @description This method sends a GET request to the specified endpoint with
	 *              the necessary authorization header to retrieve details of
	 *              various pharmacy stores. The API response provides details such
	 *              as `StoreId` and `Name` for each store.
	 *
	 *              The method performs the following validations: 1. Confirms that
	 *              the API response status code is 200 (OK). 2. Iterates through
	 *              each store in the "Results" field to ensure that both `StoreId`
	 *              and `Name` are present and not null, verifying that each store
	 *              entry is valid. 3. Asserts that the `Status` field in the
	 *              response is "OK" to confirm a successful response.
	 *
	 * @return CustomResponse - The API's response after attempting to retrieve the
	 *         list of pharmacy stores, which includes the HTTP status code, status
	 *         message, and store details within the "Results" field.
	 */
	public CustomResponse PharmacyStoresWithAuth(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test7 This method activates a pharmacy counter using counter details from an
	 *        Excel sheet.
	 *
	 * @description This method retrieves counter information (ID and name), sends a
	 *              PUT request to activate the specified pharmacy counter, and
	 *              validates the response. The API endpoint includes query
	 *              parameters for `counterId` and `counterName`, and returns
	 *              details on the activated counter.
	 *
	 *              The method performs the following validations: 1. Asserts that
	 *              the API response status code is 200 (OK). 2. Checks the
	 *              `Results` field to confirm that `CounterName` and `CounterId`
	 *              are not null, ensuring the counter details are returned as
	 *              expected. 3. Verifies that the `Status` field is "OK" to confirm
	 *              a successful activation.
	 *
	 * @return CustomResponse - The API's response, which includes the HTTP status
	 *         code, status message, and details of the activated counter in the
	 *         "Results" field.
	 */
	public CustomResponse ActivatePharmCount(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the PUT request
		Response response = request.put(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test8 This method deactivates a pharmacy counter.
	 * @description This method sends a request to deactivate a pharmacy counter and
	 *              verifies the API response. The endpoint does not require query
	 *              parameters, and the response provides details of the
	 *              deactivation result, including a `StatusCode` and `Status`
	 *              field.
	 *
	 *              The method performs the following validations: 1. Asserts that
	 *              the API response status code is 200 (OK). 2. Checks the
	 *              `Results` field to confirm that `StatusCode` is "200", verifying
	 *              successful deactivation. 3. Verifies that the `Status` field is
	 *              "OK" to confirm the operation's success.
	 *
	 * @return CustomResponse - The API's response, which includes the HTTP status
	 *         code, status message, and deactivation details in the "Results"
	 *         field.
	 */
	public CustomResponse DeactivatePharmCount(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the PUT request
		Response response = request.put(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test9 This method retrieves and verifies the list of appointment applicable
	 *        departments.
	 * @description This method sends a request to fetch all departments where
	 *              appointments are applicable, and validates the API response. The
	 *              method iterates through each department in the response,
	 *              checking essential department information.
	 *
	 *              The method performs the following validations: 1. Asserts that
	 *              the API response status code is 200 (OK). 2. Checks each item in
	 *              the `Results` list, ensuring `DepartmentId` and `DepartmentName`
	 *              fields are not null, confirming the department data's presence.
	 *              3. Verifies that the `Status` field is "OK" to indicate the
	 *              operation's success.
	 *
	 * @return CustomResponse - The API's response includes the HTTP status code,
	 *         status message, and a list of applicable departments in the "Results"
	 *         field.
	 */
	public CustomResponse AppointApplicDept(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test10 This method retrieves and verifies the list of currently admitted
	 *         patients.
	 * 
	 * @description This method sends a request to fetch data on patients with an
	 *              "admitted" status, and validates the API response. It iterates
	 *              through each patient's data in the response, checking key
	 *              information such as PatientId and AdmittedDate.
	 *
	 *              The method performs the following validations: 1. Asserts that
	 *              the API response status code is 200 (OK). 2. For each patient in
	 *              the `Results` list: - Ensures `PatientId` and `AdmittedDate`
	 *              fields are not null, confirming patient data availability. -
	 *              Verifies that `DischargedDate` is null, indicating the patient
	 *              is currently admitted. 3. Verifies that the `Status` field in
	 *              the response is "OK" to confirm a successful operation.
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of admitted patients in the "Results"
	 *         field.
	 */
	public CustomResponse admittedPatientData(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test11 This method retrieves the profile details of patients with an
	 *         "admitted" status.
	 * 
	 * @description This method sends a request to fetch data on profile details of
	 *              the account user by Bearer token and returns the API response
	 *              containing the profile details.
	 *
	 * @param endpoint - The specific API endpoint to retrieve patient data.
	 * @param body     - The request body containing optional query parameters or
	 *                 filters, if applicable.
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and profile details of the admitted patient in the
	 *         "Results" field.
	 */
	public CustomResponse getProfileDetails(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test12 @PL2 This method adds a new Department with description and other
	 *         details.
	 * 
	 * @param endpoint - The API endpoint to which the request is sent.
	 * @param body     - A map containing the department details (DepartmentCode,
	 *                 DepartmentName, etc.).
	 * @description This method constructs a JSON payload from the given map, sends
	 *              a POST request to the specified endpoint with the authorization
	 *              header, and returns the response.
	 * @return CustomResponse - The response from the API after attempting to add a
	 *         department.
	 */
	public CustomResponse addDepartment(String endpoint, Map<String, String> body) {

		String departmentCode = body.get("DepartmentCode");
		String departmentName = body.get("DepartmentName");

		// Construct the JSON payload as a string
		String requestBody = String.format(
				"{\n" + "    \"DepartmentCode\": \"%s\",\n" + "    \"DepartmentName\": \"%s\"\n" + "}", departmentCode,
				departmentName);

		// Send the POST request
		Response response = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader()) // Adding
																									// authorization
																									// header
				.header("Content-Type", "application/json") // Setting content type as JSON
				.body(requestBody) // Adding the request payload as a JSON string
				.post(BASE_URL + endpoint) // Sending POST request to the specified endpoint
				.then().extract().response(); // Extracting the response

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test13 @PL2 This method retrieves and verifies the list of departments.
	 * @description This method sends a request to fetch all departments and
	 *              validates the API response. The method iterates through each
	 *              department in the response, checking essential department
	 *              information.
	 *
	 *              The method performs the following validations: 1. Asserts that
	 *              the API response status code is 200 (OK). 2. Checks each item in
	 *              the `Results` list, ensuring `DepartmentId`, `DepartmentCode`,
	 *              and `DepartmentName` fields are not null, confirming the
	 *              department data's presence. 3. Verifies that the `Status` field
	 *              is "OK" to indicate the operation's success.
	 *
	 * @return CustomResponse - The API's response includes the HTTP status code,
	 *         status message, and a list of departments in the "Results" field.
	 */
	public CustomResponse getDepartmentsList(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test14 @PL2 This method retrieves and verifies the list of signatories by
	 *         department.
	 * @param endpoint - The API endpoint to which the PUT request is sent.
	 * @param body     - A map containing the department details.
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and updated department details in the "Results"
	 *         field.
	 */
	public CustomResponse editDepartmentDetails(String endpoint, Object body) {

		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the PUT request
		Response response = request.put(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		Map<String, Object> results = response.jsonPath().getMap("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test15_PL2 This method retrieves and verifies the list of signatories by
	 *             department.
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of imaging types in the "Results" field,
	 *         containing details such as ImagingTypeId and ImagingTypeName.
	 */
	public CustomResponse getImagingDataResponse(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

	/**
	 * @Test16_PL2 This method retrieves and verifies the list of signatories by
	 *             department.
	 * @param endpoint - The API endpoint to which the GET request is sent.
	 * @param body     - Optional
	 *
	 * @return CustomResponse - The API response includes the HTTP status code,
	 *         status message, and a list of signatories in the "Results" field,
	 *         containing details such as EmployeeId, FirstName, and LastName.
	 */
	public CustomResponse getsignatoriesDetails(String endpoint, Object body) {
		RequestSpecification request = RestAssured.given().header("Authorization", AuthUtil.getAuthHeader())
				.header("Content-Type", "application/json");

		// Only add the body if it's not null
		if (body != null) {
			request.body(body);
		}

		// Send the GET request
		Response response = request.get(BASE_URL + endpoint).then().extract().response();

		// Extract necessary details from the response
		int statusCode = response.statusCode();
		String status = response.jsonPath().getString("Status");
		List<Map<String, Object>> results = response.jsonPath().getList("Results");

		// Return a CustomResponse object with the necessary information
		return new CustomResponse(response, statusCode, status, results);
	}

}