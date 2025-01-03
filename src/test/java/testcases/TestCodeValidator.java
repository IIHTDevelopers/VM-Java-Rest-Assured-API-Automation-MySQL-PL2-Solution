package testcases;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rest.CustomResponse;

public class TestCodeValidator {

	// Method to validate if specific keywords are used in the method's source code
	public static boolean validateTestMethodFromFile(String filePath, String methodName, List<String> keywords)
			throws IOException {
		// Read the content of the test class file
		String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));

		// Extract the method body for the specified method using regex
		String methodRegex = "(public\\s+CustomResponse\\s+" + methodName + "\\s*\\(.*?\\)\\s*\\{)([\\s\\S]*?)}";
		Pattern methodPattern = Pattern.compile(methodRegex);
		Matcher methodMatcher = methodPattern.matcher(fileContent);

		if (methodMatcher.find()) {

			String methodBody = fetchBody(filePath, methodName);

			// Now we validate the method body for the required keywords
			boolean allKeywordsPresent = true;

			// Loop over the provided keywords and check if each one is present in the
			// method body
			for (String keyword : keywords) {
				Pattern keywordPattern = Pattern.compile("\\b" + keyword + "\\s*\\(");
				if (!keywordPattern.matcher(methodBody).find()) {
					System.out.println("'" + keyword + "()' is missing in the method.");
					allKeywordsPresent = false;
				}
			}

			return allKeywordsPresent;

		} else {
			System.out.println("Method " + methodName + " not found in the file.");
			return false;
		}
	}

	// This method takes the method name as an argument and returns its body as a
	// String.
	public static String fetchBody(String filePath, String methodName) {
		StringBuilder methodBody = new StringBuilder();
		boolean methodFound = false;
		boolean inMethodBody = false;
		int openBracesCount = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			while ((line = br.readLine()) != null) {
				// Check if the method is found by matching method signature
				if (line.contains("public CustomResponse " + methodName + "(")
						|| line.contains("public String " + methodName + "(")
						|| line.contains("public Response " + methodName + "(")) {
					methodFound = true;
				}

				// Once the method is found, start capturing lines
				if (methodFound) {
					if (line.contains("{")) {
						inMethodBody = true;
						openBracesCount++;
					}

					// Capture the method body
					if (inMethodBody) {
						methodBody.append(line).append("\n");
					}

					// Check for closing braces to identify the end of the method
					if (line.contains("}")) {
						openBracesCount--;
						if (openBracesCount == 0) {
							break; // End of method body
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return methodBody.toString();
	}

	public static boolean validateResponseFields(String methodName, CustomResponse customResponse) {
		boolean isValid = true;

		switch (methodName) {
		case "createAppointmentWithAuth":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String appointmentStatusField = customResponse.getResponse().jsonPath().getString("Status");
			if (appointmentStatusField == null || !appointmentStatusField.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate AppointmentId field inside Results
			Object appointmentIdObj = customResponse.getResponse().jsonPath().get("Results.AppointmentId");
			if (appointmentIdObj == null || !(appointmentIdObj instanceof Integer) || (Integer) appointmentIdObj == 0) {
				isValid = false;
				System.out.println("AppointmentId is missing or invalid in the response.");
			}

			break;

		case "cancelAppointmentWithAuth":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields1 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields1) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String appointmentStatusField1 = customResponse.getResponse().jsonPath().getString("Status");
			if (appointmentStatusField1 == null || !appointmentStatusField1.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate the Results field for the success message
			String resultMessage = customResponse.getResponse().jsonPath().getString("Results");
			if (resultMessage == null || !resultMessage.equals("Appointment information updated successfully.")) {
				isValid = false;
				System.out.println("Results field is missing or the message is incorrect in the response.");
			}

			break;
		case "searchPatientWithAuth":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields11 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields11) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String appointmentStatusField11 = customResponse.getResponse().jsonPath().getString("Status");
			if (appointmentStatusField11 == null || !appointmentStatusField11.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate the Results field (list of matching patients)
			List<Object> results = customResponse.getResponse().jsonPath().getList("Results");
			if (results == null || results.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			break;

		case "bookingListWithAuthInRange":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields111 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields111) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status = customResponse.getResponse().jsonPath().getString("Status");
			if (status == null || !status.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field is not empty
			List<Map<String, Object>> results1 = customResponse.getResponse().jsonPath().getList("Results");
			if (results1 == null || results1.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			break;

		case "MainStoreDetailsWithAuth":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields1111 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields1111) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status1 = customResponse.getResponse().jsonPath().getString("Status");
			if (status1 == null || !status1.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field is not empty and contains necessary fields
			Map<String, Object> results11 = customResponse.getResponse().jsonPath().getMap("Results");
			if (results11 == null || results11.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Validate necessary fields inside the 'Results' object
			if (!results11.containsKey("Name") || !results11.containsKey("StoreDescription")
					|| !results11.containsKey("StoreId")) {
				isValid = false;
				System.out.println("Required fields are missing in the Results: Name, StoreDescription, or StoreId.");
			}

			break;

		case "PharmacyStoresWithAuth":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields11111 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields11111) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status11 = customResponse.getResponse().jsonPath().getString("Status");
			if (status11 == null || !status11.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field is not empty and contains necessary fields
			List<Map<String, Object>> results111 = customResponse.getResponse().jsonPath().getList("Results");
			if (results111 == null || results111.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Validate necessary fields inside each store object in 'Results'
			for (Map<String, Object> result : results111) {
				if (!result.containsKey("StoreId") || !result.containsKey("Name")) {
					isValid = false;
					System.out.println("Required fields are missing in the Results: StoreId, Name.");
				}
			}

			break;

		case "ActivatePharmCount":
			// List of fields to check at the top level
			List<String> expectedTopLevelFie = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFie) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String statu = customResponse.getResponse().jsonPath().getString("Status");
			if (statu == null || !statu.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'CounterName' and 'CounterId'
			Map<String, Object> result = customResponse.getResponse().jsonPath().getMap("Results");
			if (result == null || result.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			if (!result.containsKey("CounterName") || !result.containsKey("CounterId")) {
				isValid = false;
				System.out.println("Missing required fields in the Results: CounterName, CounterId.");
			}

			break;

		case "DeactivatePharmCount":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields1a = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields1a) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status1a = customResponse.getResponse().jsonPath().getString("Status");
			if (status1a == null || !status1a.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'StatusCode'
			Map<String, Object> results1b = customResponse.getResponse().jsonPath().getMap("Results");
			if (results1b == null || results1b.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			if (!results1b.containsKey("StatusCode")) {
				isValid = false;
				System.out.println("Missing required field in the Results: StatusCode.");
			}

			break;

		case "AppointApplicDept":
			// List of fields to check at the top level
			List<String> expectedTopLevel = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevel) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String s = customResponse.getResponse().jsonPath().getString("Status");
			if (s == null || !s.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains departments with 'DepartmentId' and
			// 'DepartmentName'
			List<Map<String, Object>> r = customResponse.getResponse().jsonPath().getList("Results");
			if (r == null || r.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Check each department entry for necessary fields
			for (Map<String, Object> r2 : r) {
				if (!r2.containsKey("DepartmentId") || !r2.containsKey("DepartmentName")) {
					isValid = false;
					System.out.println("Missing required fields in the Results: DepartmentId, DepartmentName.");
				}
			}

			break;

		case "admittedPatientData":
			// List of fields to check at the top level
			List<String> e = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : e) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String st = customResponse.getResponse().jsonPath().getString("Status");
			if (st == null || !st.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains the necessary fields: PatientId,
			// AdmittedDate, and DischargedDate
			List<Map<String, Object>> re1 = customResponse.getResponse().jsonPath().getList("Results");
			if (re1 == null || re1.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Check each patient entry for necessary fields
			for (Map<String, Object> re2 : re1) {
				if (!re2.containsKey("PatientId") || !re2.containsKey("AdmittedDate")) {
					isValid = false;
					System.out.println("Missing required fields in the Results: PatientId, AdmittedDate.");
				}

				// Ensure that 'DischargedDate' is null for admitted patients
				if (re2.containsKey("DischargedDate") && re2.get("DischargedDate") != null) {
					isValid = false;
					System.out.println("DischargedDate should be null for admitted patients.");
				}
			}

			break;

		case "getProfileDetails":
			// List of fields to check at the top level
			List<String> expected11 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expected11) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String stat11 = customResponse.getResponse().jsonPath().getString("Status");
			if (stat11 == null || !stat11.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'EmployeeId' and other required
			// fields
			Map<String, Object> r11 = customResponse.getResponse().jsonPath().getMap("Results");
			if (r11 == null || r11.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			if (!r11.containsKey("EmployeeId")) {
				isValid = false;
				System.out.println("Missing required field in the Results: EmployeeId.");
			}

			break;

		case "addDepartment":
			// List of fields to check at the top level
			List<String> expec12 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expec12) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String st12 = customResponse.getResponse().jsonPath().getString("Status");
			if (st12 == null || !st12.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'DepartmentId', 'DepartmentCode',
			// and 'DepartmentName'
			Map<String, Object> res12 = customResponse.getResponse().jsonPath().getMap("Results");
			if (res12 == null || res12.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			if (!res12.containsKey("DepartmentId") || !res12.containsKey("DepartmentCode")
					|| !res12.containsKey("DepartmentName")) {
				isValid = false;
				System.out.println(
						"Missing required fields in the Results: DepartmentId, DepartmentCode, DepartmentName.");
			}

			break;

		case "getDepartmentsList":
			// List of fields to check at the top level
			List<String> expected13 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expected13) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status13 = customResponse.getResponse().jsonPath().getString("Status");
			if (status13 == null || !status13.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'DepartmentId', 'DepartmentCode',
			// and 'DepartmentName'
			List<Map<String, Object>> results13 = customResponse.getResponse().jsonPath().getList("Results");
			if (results13 == null || results13.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Check each department entry for necessary fields
			for (Map<String, Object> result13 : results13) {
				if (!result13.containsKey("DepartmentId") || !result13.containsKey("DepartmentCode")
						|| !result13.containsKey("DepartmentName")) {
					isValid = false;
					System.out.println(
							"Missing required fields in the Results: DepartmentId, DepartmentCode, DepartmentName.");
				}
			}

			break;

		case "editDepartmentDetails":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields14 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields14) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status14 = customResponse.getResponse().jsonPath().getString("Status");
			if (status14 == null || !status14.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'DepartmentId', 'DepartmentCode',
			// and 'DepartmentName'
			Map<String, Object> results14 = customResponse.getResponse().jsonPath().getMap("Results");
			if (results14 == null || results14.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			if (!results14.containsKey("DepartmentId") || !results14.containsKey("DepartmentCode")
					|| !results14.containsKey("DepartmentName")) {
				isValid = false;
				System.out.println(
						"Missing required fields in the Results: DepartmentId, DepartmentCode, DepartmentName.");
			}

			break;

		case "getImagingDataResponse":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields15 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields15) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status15 = customResponse.getResponse().jsonPath().getString("Status");
			if (status15 == null || !status15.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'ImagingTypeId' and
			// 'ImagingTypeName'
			List<Map<String, Object>> results15 = customResponse.getResponse().jsonPath().getList("Results");
			if (results15 == null || results15.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Check each imaging type entry for necessary fields
			for (Map<String, Object> result15 : results15) {
				if (!result15.containsKey("ImagingTypeId") || !result15.containsKey("ImagingTypeName")) {
					isValid = false;
					System.out.println("Missing required fields in the Results: ImagingTypeId, ImagingTypeName.");
				}
			}

			break;

		case "getsignatoriesDetails":
			// List of fields to check at the top level
			List<String> expectedTopLevelFields16 = List.of("Status");

			// Print the response for debugging
			System.out.println(customResponse.getResponse().prettyPrint());

			// Validate the response structure for required top-level fields
			for (String field : expectedTopLevelFields16) {
				if (customResponse.getResponse().jsonPath().get(field) == null) {
					isValid = false;
					System.out.println("Missing field in response: " + field);
				}
			}

			// Validate the Status field at the top level
			String status16 = customResponse.getResponse().jsonPath().getString("Status");
			if (status16 == null || !status16.equals("OK")) {
				isValid = false;
				System.out.println("Status field is missing or invalid in the response.");
			}

			// Validate that the Results field contains 'EmployeeId', 'FirstName', and
			// 'LastName'
			List<Map<String, Object>> results16 = customResponse.getResponse().jsonPath().getList("Results");
			if (results16 == null || results16.isEmpty()) {
				isValid = false;
				System.out.println("Results field is missing or empty in the response.");
			}

			// Check each signatory entry for necessary fields
			for (Map<String, Object> result16 : results16) {
				if (!result16.containsKey("EmployeeId") || !result16.containsKey("FirstName")
						|| !result16.containsKey("LastName")) {
					isValid = false;
					System.out.println("Missing required fields in the Results: EmployeeId, FirstName, LastName.");
				}
			}

			break;

		default:
			System.out.println("Method " + methodName + " is not recognized for validation.");
			isValid = false;
		}
		return isValid;
	}

}