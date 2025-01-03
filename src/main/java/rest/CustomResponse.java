package rest;

import java.util.List;
import java.util.Map;

import io.restassured.response.Response;

public class CustomResponse {
	private Response response;
	private int statusCode;
	private String status;
	private Integer appointmentId;
	private List<Map<String, Object>> listResults;
	private String resultMessage;
	private Map<String, Object> mapResults;
	private List<Object> itemIds;
	private List<Object> itemNames;
	private List<Object> genericNames;
	private Object storeId;
	private Object category;
	private Object isActive;
	private Object patientId;
	private Object totalDue;
	private List<Object> patientIds;
	private List<Object> patientCodes;

	public CustomResponse(Response response, int statusCode, String status, Integer appointmentId) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.appointmentId = appointmentId;
	}

	public CustomResponse(Response response, int statusCode, String status, Map<String, Object> mapResults) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.mapResults = mapResults;
	}

	public CustomResponse(Response response, int statusCode, String status, String resultMessage) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.resultMessage = resultMessage;
	}

	public CustomResponse(Response response, int statusCode, String status, List<Map<String, Object>> listResults) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.listResults = listResults;
	}

	public CustomResponse(Response response, int statusCode, String status, List<Object> patientIds,
			List<Object> patientCodes) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.patientIds = patientIds;
		this.patientCodes = patientCodes;
	}

	public CustomResponse(Response response, int statusCode, String status, List<Object> itemIds,
			List<Object> itemNames, List<Object> genericNames) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.itemIds = itemIds;
		this.itemNames = itemNames;
		this.genericNames = genericNames;
	}

	public CustomResponse(Response response, int statusCode, String status, Object storeId, Object category,
			Object isActive) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.storeId = storeId;
		this.category = category;
		this.isActive = isActive;
	}

	public CustomResponse(Response response, int statusCode, String status, Object patientId, Object totalDue) {
		this.response = response;
		this.statusCode = statusCode;
		this.status = status;
		this.patientId = patientId;
		this.totalDue = totalDue;
	}

	public Response getResponse() {
		return response;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public String getStatus() {
		return status;
	}

	public Integer getAppointmentId() {
		return appointmentId;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public List<Map<String, Object>> getListResults() {
		return listResults;
	}

	public void setListResults(List<Map<String, Object>> listResults) {
		this.listResults = listResults;
	}

	public Map<String, Object> getMapResults() {
		return mapResults;
	}

	public void setMapResults(Map<String, Object> mapResults) {
		this.mapResults = mapResults;
	}

	public List<Object> getItemIds() {
		return itemIds;
	}

	public void setItemIds(List<Object> itemIds) {
		this.itemIds = itemIds;
	}

	public List<Object> getItemNames() {
		return itemNames;
	}

	public void setItemNames(List<Object> itemNames) {
		this.itemNames = itemNames;
	}

	public List<Object> getGenericNames() {
		return genericNames;
	}

	public void setGenericNames(List<Object> genericNames) {
		this.genericNames = genericNames;
	}

	public Object getStoreId() {
		return storeId;
	}

	public void setStoreId(Object storeId) {
		this.storeId = storeId;
	}

	public Object getCategory() {
		return category;
	}

	public void setCategory(Object category) {
		this.category = category;
	}

	public Object getIsActive() {
		return isActive;
	}

	public void setIsActive(Object isActive) {
		this.isActive = isActive;
	}

	public Object getPatientId() {
		return patientId;
	}

	public void setPatientId(Object patientId) {
		this.patientId = patientId;
	}

	public Object getTotalDue() {
		return totalDue;
	}

	public void setTotalDue(Object totalDue) {
		this.totalDue = totalDue;
	}

	public List<Object> getPatientIds() {
		return patientIds;
	}

	public void setPatientIds(List<Object> patientIds) {
		this.patientIds = patientIds;
	}

	public List<Object> getPatientCodes() {
		return patientCodes;
	}

	public void setPatientCodes(List<Object> patientCodes) {
		this.patientCodes = patientCodes;
	}
}
