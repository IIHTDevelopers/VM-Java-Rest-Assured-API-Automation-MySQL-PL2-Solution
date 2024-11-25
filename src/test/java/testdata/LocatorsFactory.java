package testdata;

import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import testBase.UserActions;

public class LocatorsFactory extends UserActions {

	UserActions userActions = new UserActions(driver);

	// element of health application

	By totalDoctortextElement = By.xpath("//p[contains(text(), 'Total Doctors')]");
	By selectCounterPopupElement = By.xpath("//h3//span[contains(text(), 'Select Counter')]");
	By new1TextElement = By.xpath("//h5[contains(text(), 'New-1')]");
	By new2TextElement = By.xpath("//h5[contains(text(), 'New-2')]");
	By new3TextElement = By.xpath("//h5[contains(text(), 'New-3')]");
	By old1TextElement = By.xpath("//h5[contains(text(), 'Old-1')]"); 
	By opdCounterTextElement = By.xpath("//h5[contains(text(), 'OPD-Counter')]");
	By confirmMessage = By.xpath("//p[.='Are you sure you want to Proceed ?']");
	By mainStoreDropDown = By.xpath("//option[.='Main store']");

	By addNewPatientButtonElement = By.xpath("//button[contains(text(), 'Add New Patient')]");
	By registerAndBillingButtonElement = By.xpath("//button[contains(text(), 'Register & Billing')]");
	By registerOnlyButtonElement = By.xpath("//button[contains(text(), 'Register Only')]");
	By registerOnlyButtonToHighlight = By.xpath("(//button[@class=\"btn green btn-success margin-7-hr\"])[2]");
	By firstNameTextFieldElement = By.xpath("//input[@id='newPatFirstName']");
	By countryDropdownByElement = By.xpath("//select[@id='ddlCountry']");
	By errorMessageOfRegisterBillingByElement = By.xpath("//p[contains(text(), 'Some of the inputs are invalid. Please check and try again. !')]");
	By closeButtonOfAddNewpatientpage = By.xpath("//button[contains(text(), 'Close')]");
	By ageTextFieldInAddNewPatient = By.id("Age");

	By errorMessageCloseButton = By.xpath("//a[@class='close-btn']//i[@class='fa fa-times-circle']");
	By dispensaryToggle = By.xpath("//span[@data-target='#Dispensary']");
	By prescriptionSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Prescription')]");
	By saleSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Sale')]");
	By stockSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Stock')]");
	By counterSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Counter')]");
	By reportsSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Reports')]");
	By patientConsumptionSubMenu = By.xpath("//ul[@id='Dispensary']//li//a//span[contains(text(),'Patient Consumption')]");
	By addRequisitionsPageName = By.xpath("//span[.='Add Requisition']");

	//	By dispensaryLeftNavigationMenu = By.xpath("//a[@class='left-nav-active']//span[contains(text(), 'Dispensary')]");
	By dispensaryLeftNavigationMenu = By.xpath("//a[@href='#/Dispensary']//span[contains(text(), 'Dispensary')]");
	By settingLeftNavigationMenu = By.xpath("//a[@href='#/Settings']//span[contains(text(), 'Settings')]");
	By printInvoiceButtonElement = By.xpath("//input[@value='Print Invoice']");

	//l2 locators

	By checkBox=By.xpath("(//label[@class='mt-checkbox mt-checkbox-outline'])[1]//span");
	By addNewButtonElement = By.xpath("//button[contains(text(), 'Add New Patient')]");
	By headerNotificationBar = By.id("header_notification_bar");
	By admittingDocField = By.xpath("//b[.='Admitting Doc:']");
	By newItemButton = By.xpath("//button[.=' New Item ']");
	By myFavoritesButton = By.xpath("//a[.=' My Favorites']");
	By manageRoleTab = By.xpath("//a[.='Manage Role']");
	By tooltipText = By.xpath("//li[contains(text(),'+')]");

	//Dispensary module locators
	By registeredPatientTextElement = By.xpath("//p[contains(text(), 'Registered Patient')]");
	By morningCounter = By.xpath("(//div[@class='counter-item'])[1]");
	By activeDispensaryField = By.xpath("//label[@class='label label-primary']");
	By searchPatientTextField = By.id("patient-search");
	By firstNameField = By.id("newPatFirstName");
	By errorMeesageInLastNameTextField = By.xpath("//span[.=' Last Name is required.']");
	By errorMeesageInGenderDropdownElement = By.xpath("//span[contains(text(), ' Gender is required')]");
	By totalStockValueText = By.xpath("//div[@class='right']");
	By creditLimitsAndBalancesTextElement = By.xpath("//b[contains(text() , 'Credit Limits and Balances')]");
	By ContactNumberTextFieldInAddNewPatient = By.id("Contact");
	By firstNameTextFieldInAddNewPatient = By.id("newPatFirstName");
	By mainStoreOption = By.xpath("//option[.='Main store']");	
	By createRequisitionsButtonElement = By.xpath("//button[@class='btn btn-primary']");
	By  createRequisitionButton = By.xpath("//button[@class='btn btn-primary']");
	By  addRequisitionText = By.xpath("//span[.='Add Requisition']");
	By remarksTextField = By.id("remark0");	
	By quantityTextField = By.xpath("//table//tr[@style='text-align:center']//td[.='1']");
	By denphehLogo=By.xpath("//div[@class='logo']");
	By dispatchedQtyField=By.xpath("//table//tr//td[.='Dispatched Qty']");
	By raceField = By.id("race");	
	By listByPatientStatusRadioButton = By.xpath("//input[@type='radio']");	
	By saveButton = By.xpath("//button[.='Save']");

	By okButton = By.xpath("//button[@id='registerPatient']");
	By printInvoiceButton = By.xpath("//input[@value='Print Invoice']");
	By addNewOtButtonElement = By.xpath("//input[@value='Add New OT']");
	By addOtherChargesButton = By.xpath("//input[@value='Add Other Charges']");

	//Social service Module(L1)
	By editInformationOfTextField = By.id("allPatWithOutIns");
	By  SSUPatientListButton= By.xpath("//a[.=' SSU Patient List ']");
	By registerNewSSUPatientButton = By.xpath("//a[.=' Register New SSU Patient']");	
	By patientInformationTitle = By.xpath("//h4[.=' Patient Information ']");
	By firstNameTextField = By.id("regPatFirstName");	
	By phoneNumberTextField = By.id("PhoneNo");
	By lastNameTextField = By.id("LastName");
	By phoneNumberTextFieldErrorMessage = By.xpath("//span[.='Primary Phone is not proper']");
	By warningMessage = By.xpath("(//span[.='Membership Scheme(s) is Mandatory. '])[2]");
	By hasTG_certificateDropdown = By.id("hasTG_certificate");
	//	By raceField = By.id("race");
	By newSsuPatientRegistrationTextField = By.xpath("//strong[.='New SSU Patient Registration']");
	By closeButton = By.xpath("//button[.=' Close ']");
	By countryDropdown = By.id("ddlCountry");
	By crossButtonElement = By.xpath("//button[contains(text(), 'X')]");
	By remarkTextField = By.id("remark1");
	By requisitionsListButton = By.xpath("//a[@class='btn btn-primary']");
	By medicineQuantity = By.xpath("(//table[@class='table table-striped table-hover req_table']//tr[@style='text-align:center']//td)[2]");
	By newSSUPatientRegistrationFormsCloseButtonElement = By.xpath("//button[contains(text(), 'Close ')]");
	By registerButtonOfNewSSUPatientRegistrationForms = By.xpath("//button[contains(text(), 'Register')]");
	By patientInformationTabElement = By.xpath("//h4[contains(text(), 'Patient Information')]");
	By certificateNoTextbox = By.xpath("//input[@id='TG_CertificateNo']");
	By editInformationOfExistingPatientNameByElement = By.id("allPatWithOutIns");
	By cuttonSubCategoryNameByElement = By.xpath("//div[contains(text(), 'cotton')]");
	By saveItemByElement = By.xpath("//input[@id='AddItem']");
	By activeRadioButton = By.xpath("(//label//span)[2]");
	By commonRadioButtonByElement = By.xpath("//input[@id='Common']");
	By addSubCategoryButtonByElement = By.xpath("//input[@value='Add Sub Category']");



	//Social service Module(L2)

	//	By saveButton = By.xpath("//button[.='Save']");
	By submitButton = By.id("btn-add");
	By accountOption = By.xpath("//span[.='Accounts']");
	By printButton = By.xpath("//button[.='Print']");



	//OperationTheatre Module(L1)

	//	By printButton = By.xpath("//button[.='Print']");
	By ssuPatientListText = By.xpath("//a[.=' SSU Patient List ']");
	By vatInPercentageTextFieldElement = By.xpath("//input[@id='VAT']");
	By newOTBookingButtonByElement = By.xpath("//button[contains(text(), 'New OT Booking')]");
	By addNewOTButtonByElement = By.xpath("//input[@value='Add New OT']");
	By surgeryTypeTextboxElement = By.xpath("//textarea[@id='surgeryType']");
	By surgenNameFirstButtonElement = By.xpath("//ul[@class='select-list']//li[1]");
	By plusIconButtonElement = By.xpath("//a[@class='btn blue btn-xs btn-ext']");
	By errorMessageOfAddExternalReferralFormsByElement = By.xpath("//span[contains(text(), 'Referrer Name is required')]");
	By successNotificationPopupMessageOneElement = By.xpath("//p[@class='msg-status']");
	By successNotificationPopupMessageTwoElement = By.xpath("//p[@class='main-message']");
	By successNotificationPopupMessageElement = By.xpath("//div[@class='msg-text padding-8-all']");
	By monthRadioButtonByElement = By.xpath("//input[@value='M']");
	By daysRadioButtonByElement = By.xpath("//input[@value='D']");

	By selectVisitTypeDropdownByElement = By.xpath("//select[@name='VistType']");

	By handOverToUserButtonOfBillingSubModuleByElement = By.xpath("//input[@value='HandOver To User']");
	By remarksTextFieldOfBillingSubModulesByElement = By.xpath("//input[@id='HandoverRemarks_User']");
	By titleNameOfVaccinationPatientRegisterFormByElement = By.xpath("//span[contains(text(), 'Vaccination Patient Register')]");
	By errorNotificationPopupMessageElement = By.xpath("//div[@class='msg-text padding-8-all']");
	By statusErrorNotificationMessageByElement = By.xpath("//p[@class='msg-status']");
	By mainErrorNotificationMessageByElement = By.xpath("//p[@class='main-message']");
	
	By selectOTAssistantNameFieldByElement = By.xpath("//span[contains(text(), '---Select Ot Assistant Name---')]");
	By searchTexboxOfSelectOTAssistantNameFieldByElement = By.xpath("//input[@placeholder='Search']");
	By drPoojaMishraCheckBoxByElement = By.xpath("//label[contains(text(), 'Dr. pooja Mishra')]/..//input");
	By selectOTAssistantNameDropdownByElement = By.xpath("//div[@class='cuppa-dropdown']");
	
	By discardButtonByElement = By.xpath("//input[@value='Discard Changes']");


	public LocatorsFactory(WebDriver driver) {
		super(driver);

	}


	public WebElement totalDoctorTextIsPresent(WebDriver driver) {
		WebElement totalDoctorTextWebElement = driver.findElement(totalDoctortextElement);
		userActions.highlightElement(totalDoctorTextWebElement);
		return totalDoctorTextWebElement; 
	}

	public Boolean verifyAllFieldIsPresent() throws Exception {
		Boolean allFieldIsPresent = false;
		try {
			if(userActions.isDisplayed(selectCounterPopupElement)&&
					userActions.isDisplayed(new1TextElement)&&
					userActions.isDisplayed(new2TextElement)&&
					userActions.isDisplayed(new3TextElement)&&
					userActions.isDisplayed(old1TextElement)&&
					userActions.isDisplayed(opdCounterTextElement)) {

				allFieldIsPresent = true;
			}	
		}catch(Exception e) {
			throw e;	
		}
		return allFieldIsPresent;
	}

	public WebElement addNewButtonIsPresent(WebDriver driver) {
		WebElement addNewPatientButtonWebElement = driver.findElement(addNewPatientButtonElement);
		userActions.highlightElementAfterAction(addNewPatientButtonWebElement);
		return addNewPatientButtonWebElement; 
	}

	public WebElement registerAndBillingButtonIsPresent(WebDriver driver) {
		WebElement registerAndBillingButtonWebElement = driver.findElement(registerAndBillingButtonElement);
		return registerAndBillingButtonWebElement; 
	}

	public String verifyFirstNameTextValueIsPresent() throws Exception {
		String firstNameTextfieldValue = "";
		try {
			if(userActions.isDisplayed(firstNameTextFieldElement))
			{
				firstNameTextfieldValue = userActions.getAttribute(firstNameTextFieldElement, "value");
				System.out.println("firstName Text value is  : " + firstNameTextfieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return firstNameTextfieldValue;
	}

	public String verifyRegisterOnlyButtonIsPresent() throws Exception {
		String registerOnlyButtonIsPresent = "";
		try {
			if(userActions.isDisplayed(registerOnlyButtonElement))
			{
				registerOnlyButtonIsPresent = userActions.getText(registerOnlyButtonElement);
				System.out.println("text value of register Only Button : " + registerOnlyButtonIsPresent);
				WebElement registerOnlyButtonWebElement = userActions.findElement(registerOnlyButtonToHighlight);
				userActions.highlightElementAfterAction(registerOnlyButtonWebElement);
			}
		}catch(Exception e) {
			throw e;
		}
		return registerOnlyButtonIsPresent;
	}

	public String verifyIndiaIsPresent() throws Exception {
		String selectedCountryValue = "";
		try {
			if(userActions.isDisplayed(countryDropdownByElement))
			{
				selectedCountryValue = userActions.getFirstSelectedOptionFromDropdown(countryDropdownByElement, "elementName", "pageName");
				System.out.println("selected dropdown value is  : " + selectedCountryValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return selectedCountryValue;
	}


	public String verifyErrorMessageIsPresent() throws Exception {
		String errorMessageValue = "";
		try {
			if(userActions.isDisplayed(errorMessageOfRegisterBillingByElement))
			{
				errorMessageValue =  userActions.getText(errorMessageOfRegisterBillingByElement);
				System.out.println("error message validation by Locators factory class : " + errorMessageValue);
				userActions.jsClick(errorMessageCloseButton);
				userActions.jsClick(closeButtonOfAddNewpatientpage);
			}
		}catch(Exception e) {
			throw e;
		}
		return errorMessageValue;
	}

	public Boolean highLightPresenceOfAllFieldInDispensaryMenu() throws Exception {
		Boolean highlightPresenceOfElement=false;
		try {
			if(userActions.isDisplayed(prescriptionSubMenu)&&
					userActions.isDisplayed(saleSubMenu)&&
					userActions.isDisplayed(stockSubMenu)&&
					userActions.isDisplayed(counterSubMenu)&&
					userActions.isDisplayed(reportsSubMenu)&&
					userActions.isDisplayed(patientConsumptionSubMenu)) {

				WebElement prescriptionSubMenuWebElement = driver.findElement(prescriptionSubMenu);
				userActions.highlightElementAfterAction(prescriptionSubMenuWebElement);
				WebElement saleSubMenuWebElement = driver.findElement(saleSubMenu);
				userActions.highlightElementAfterAction(saleSubMenuWebElement);
				WebElement stockSubMenuWebElement = driver.findElement(stockSubMenu);
				userActions.highlightElementAfterAction(stockSubMenuWebElement);
				WebElement counterSubMenuWebElement = driver.findElement(counterSubMenu);
				userActions.highlightElementAfterAction(counterSubMenuWebElement);
				WebElement reportsSubMenuWebElement = driver.findElement(reportsSubMenu);
				userActions.highlightElementAfterAction(reportsSubMenuWebElement);
				WebElement patientConsumptionSubMenuWebElement = driver.findElement(patientConsumptionSubMenu);
				userActions.highlightElementAfterAction(patientConsumptionSubMenuWebElement);

				highlightPresenceOfElement= true;
			}	

		}catch(Exception e) {
			throw e;	
		}
		return highlightPresenceOfElement;
	}

	public Boolean verifyFieldIsNotPresentInDispensaryMenu() throws Exception {
		Boolean highlightDispensaryNavigationMenu=false;
		try {
			if(!(userActions.isDisplayed(prescriptionSubMenu)&&
					userActions.isDisplayed(saleSubMenu))) {

				WebElement dispensaryNavigationMenuWebElement = driver.findElement(dispensaryLeftNavigationMenu);
				userActions.highlightElementAfterAction(dispensaryNavigationMenuWebElement);

				highlightDispensaryNavigationMenu = true;
			}	

		}catch(Exception e) {
			throw e;	
		}
		return highlightDispensaryNavigationMenu;
	}

	public Boolean settingModuleIsPresent() throws Exception {
		Boolean settingModuleIsPresent = false;
		try {
			if(userActions.isDisplayed(settingLeftNavigationMenu)) {

				WebElement settingLeftNavigationMenuWebElement = driver.findElement(settingLeftNavigationMenu);
				userActions.highlightElementAfterAction(settingLeftNavigationMenuWebElement);

				settingModuleIsPresent = true;
			}	

		}catch(Exception e) {
			throw e;	
		}
		return settingModuleIsPresent;
	}

	// l2 scenarios

	public WebElement headerNotificationBarIsPresent(WebDriver driver) {
		WebElement headerNotificationBarWebElement = driver.findElement(headerNotificationBar);
		return headerNotificationBarWebElement; 
	}

	public WebElement admittingDocFieldIsPresent(WebDriver driver) {
		WebElement admittingDocFieldWebElement = driver.findElement(admittingDocField);
		return admittingDocFieldWebElement; 
	}

	public WebElement checkBoxIsPresent(WebDriver driver) {
		WebElement checkBoxWebElement = driver.findElement(checkBox);
		return checkBoxWebElement; 
	}

	public WebElement newItemButtonPresent(WebDriver driver) {
		WebElement newItemButtonWebElement = driver.findElement(newItemButton);
		return newItemButtonWebElement; 
	}

	public WebElement myFavoritesButtonIsPresent(WebDriver driver) {
		WebElement myFavoritesButtonWebElement = driver.findElement(myFavoritesButton);
		return myFavoritesButtonWebElement; 
	}

	public WebElement manageRoleTabIsPresent(WebDriver driver) {
		WebElement manageRoleTabWebElement = driver.findElement(manageRoleTab);
		return manageRoleTabWebElement; 
	}

	public WebElement newItemButtonIsPresent(WebDriver driver) {
		WebElement newItemButtonWebElement = driver.findElement(newItemButton);
		return newItemButtonWebElement;
	}

	public WebElement tooltipTextIsPresent(WebDriver driver) {
		WebElement tooltipTextWebElement = driver.findElement(tooltipText);
		return tooltipTextWebElement;
	}

	//Dispensary module 

	public WebElement registeredPatientTextElementIsPresent(WebDriver driver) {
		WebElement registeredPatientTextElementWebElement = driver.findElement(registeredPatientTextElement);
		return registeredPatientTextElementWebElement;
	}

	public WebElement morningCounterIsPresent(WebDriver driver) {
		WebElement morningCounterWebElement = driver.findElement(morningCounter);
		return morningCounterWebElement;
	}

	public WebElement activeDispensaryFieldIsPresent(WebDriver driver) {
		WebElement activeDispensaryFiledWebElement = driver.findElement(activeDispensaryField);
		return activeDispensaryFiledWebElement;
	}

	public WebElement searchPatientTextFieldIsPresent(WebDriver driver) {
		WebElement searchPatientTextFieldWebElement = driver.findElement(searchPatientTextField);
		return searchPatientTextFieldWebElement;
	}


	public WebElement firstNameFieldIsPresent(WebDriver driver) {
		WebElement firstNameFieldWebElement = driver.findElement(firstNameField);
		return firstNameFieldWebElement;
	}

	public WebElement verifyErrorMessageIsPresentInLastNameTextField(WebDriver driver) {
		WebElement errorMeesageInLastNameTextFieldWebElement = driver.findElement(errorMeesageInLastNameTextField);
		return errorMeesageInLastNameTextFieldWebElement;
	}

	public WebElement verifyErrorMessageIsPresentInGenderDropdown(WebDriver driver) {
		WebElement errorMeesageInGenderDropdownWebElement = driver.findElement(errorMeesageInGenderDropdownElement);
		return errorMeesageInGenderDropdownWebElement;
	}

	public WebElement confirmMessageIsPresent(WebDriver driver) {
		WebElement confirmMessageWebElement = driver.findElement(confirmMessage);
		return confirmMessageWebElement;
	}
	public WebElement mainStoreDropDownIsPresent(WebDriver driver) {
		WebElement mainStoreDropDownWebElement = driver.findElement(mainStoreDropDown);
		return mainStoreDropDownWebElement;
	}

	public WebElement addRequisitionsPageNameIsPresent(WebDriver driver) {
		WebElement addRequisitionsPageNameWebElement = driver.findElement(addRequisitionsPageName);
		return addRequisitionsPageNameWebElement;
	}


	public WebElement remarkTextFieldIsPresent(WebDriver driver) {
		WebElement remarkTextFieldWebElement = driver.findElement(remarkTextField);
		return remarkTextFieldWebElement;
	}

	public WebElement medicineQuantityIsPresent(WebDriver driver) {
		WebElement medicineQuantityWebElement = driver.findElement(medicineQuantity);
		return medicineQuantityWebElement;
	}

	public WebElement requisitionsListButtonIsPresent(WebDriver driver) {
		WebElement requisitionsListButtonWebElement = driver.findElement(requisitionsListButton);
		return requisitionsListButtonWebElement;
	}
	public String ageTextFieldIsPresent() throws Exception {
		String ageTextFieldValue = "";
		try {
			if(userActions.isDisplayed(ageTextFieldInAddNewPatient))
			{
				ageTextFieldValue = userActions.getAttribute(ageTextFieldInAddNewPatient, "value");
				System.out.println("Age TextField Value  : " + ageTextFieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return ageTextFieldValue;
	}


	public String firstNameTextFieldInAddNewPatientIsPresent() throws Exception {
		String firstNameTextFieldValue = "";
		try {
			if(userActions.isDisplayed(firstNameTextFieldElement))
			{
				firstNameTextFieldValue = userActions.getAttribute(firstNameTextFieldInAddNewPatient, "value");
				System.out.println("firstName TextField Value  : " + firstNameTextFieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return firstNameTextFieldValue;
	}


	public WebElement totalStockValueTextIsPresent(WebDriver driver) {
		WebElement totalStockValueTextWebElement = driver.findElement(totalStockValueText);
		return totalStockValueTextWebElement;
	}


	public WebElement creditLimitsAndBalancesTextElementIsPresent(WebDriver driver) {
		WebElement creditLimitsAndBalancesTextElementWebElement = driver.findElement(creditLimitsAndBalancesTextElement);
		return creditLimitsAndBalancesTextElementWebElement;
	}

	public String createRequisitionsButtonIsPresent() throws Exception {
		String createRequisitionsButtonValue = "";
		try {
			if(userActions.isDisplayed(createRequisitionsButtonElement))
			{
				createRequisitionsButtonValue = userActions.getText(createRequisitionsButtonElement);
				System.out.println("create Requisitions Button Value  : " + createRequisitionsButtonValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return createRequisitionsButtonValue;
	}


	public WebElement printInvoiceButtonElementIsPresent(WebDriver driver) {
		WebElement printInvoiceButtonElementWebElement = driver.findElement(printInvoiceButtonElement);
		return printInvoiceButtonElementWebElement;
	}

	public WebElement verifyRegisterButtonIsPresent(WebDriver driver) {
		WebElement printInvoiceButtonElementWebElement = driver.findElement(registerButtonOfNewSSUPatientRegistrationForms);
		return printInvoiceButtonElementWebElement;
	}


	public WebElement mainStoreOptionIsSelected(WebDriver driver) {
		WebElement mainStoreOptionWebElement = driver.findElement(mainStoreOption);
		return mainStoreOptionWebElement;
	}

	public WebElement createRequisitionButtonIsPresent(WebDriver driver) {
		WebElement createRequisitionButtonWebElement = driver.findElement(createRequisitionButton);
		return createRequisitionButtonWebElement;
	}

	public WebElement addRequisitionTextIsPresent(WebDriver driver) {
		WebElement addRequisitionTextWebElement = driver.findElement(addRequisitionText);
		return addRequisitionTextWebElement;
	}


	public WebElement remarksTextFieldIsPresent(WebDriver driver) {
		WebElement remarksTextFieldWebElement = driver.findElement(remarksTextField);
		return remarksTextFieldWebElement;
	}


	public WebElement denphehLogoIsPresent(WebDriver driver) {
		WebElement denphehLogoWebElement = driver.findElement(denphehLogo);
		return denphehLogoWebElement;
	}

	public WebElement dispatchedQtyFieldIsPresent(WebDriver driver) {
		WebElement dispatchedQtyFieldWebElement = driver.findElement(dispatchedQtyField);
		return dispatchedQtyFieldWebElement;
	}

	public WebElement verifySaveItemIsPresent(WebDriver driver) {
		WebElement saveItemWebElement = driver.findElement(saveItemByElement);
		return saveItemWebElement;
	}

	public WebElement verifyAddSubCategoryIsPresent(WebDriver driver) {
		WebElement saveItemaddSubCategoryButtonByWebElement = driver.findElement(addSubCategoryButtonByElement);
		return saveItemaddSubCategoryButtonByWebElement;
	}

	public String raceTextFieldPlaceHolderIsPresent() throws Exception {
		String raceTextFieldPlaceHolder = "";
		try {
			if(userActions.isDisplayed(raceField))
			{
				Thread.sleep(3000);
				raceTextFieldPlaceHolder = userActions.getAttribute(raceField, "placeholder");
				System.out.println("Race TextField place holder Value  : " + raceTextFieldPlaceHolder);
			}
		}catch(Exception e) {
			throw e;
		}
		return raceTextFieldPlaceHolder;
	}

	public String verifyAttributesValueOfEditInformationTexbox() throws Exception {
		String attributesValue = "";
		try {
			if(userActions.isDisplayed(editInformationOfExistingPatientNameByElement))
			{
				Thread.sleep(3000);
				attributesValue = userActions.getAttribute(editInformationOfExistingPatientNameByElement, "value");
				System.out.println("edit Information Of Existing Patient Name's attributes value  : " + attributesValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return attributesValue;
	}

	public String veryfyTableData() throws Exception {
		String cuttonSubCategoryName = "";
		try {
			if(userActions.isDisplayed(cuttonSubCategoryNameByElement)) {
				cuttonSubCategoryName = userActions.getText(cuttonSubCategoryNameByElement);
				System.out.println("sub category name of given code 003: " + cuttonSubCategoryName);
			}
		}catch(Exception e) {
			throw e;
		}	
		return cuttonSubCategoryName;
	}

	public Boolean verifySocialServiceModuleRadioButtoIsSelected() throws Exception {
		Boolean verifyRadioButton = false;
		try {
			if(userActions.isSelected(activeRadioButton)) {
				System.out.println("active Radio button is Selected");
				verifyRadioButton = true;
			}
		}catch(Exception e) {
			throw e;
		}	
		return verifyRadioButton;
	}

	public Boolean verifyProcurementModuleRadioButton() throws Exception {
		Boolean verifyRadioButton = false;
		try {
			Thread.sleep(3000);

			if(userActions.isSelected(commonRadioButtonByElement)) {
				System.out.println("Common Radio button is selected");
				verifyRadioButton = true;
			}	
		}catch(Exception e) {
			throw e;
		}	
		return verifyRadioButton;
	}

	public WebElement listByPatientStatusRadioButtonIsPresent(WebDriver driver) {
		WebElement listByPatientStatusRadioButtonWebElement = driver.findElement(listByPatientStatusRadioButton);
		return listByPatientStatusRadioButtonWebElement;
	}

	public WebElement verifyEditInformationTextboxIsPresent(WebDriver driver) {
		WebElement editInformationTextboxWebElement = driver.findElement(editInformationOfExistingPatientNameByElement);
		return editInformationTextboxWebElement;
	}

	public WebElement verifySSUPatientListButtonIsPresent(WebDriver driver) {
		WebElement SSUPatientListButtonWebElement = driver.findElement(SSUPatientListButton);
		return SSUPatientListButtonWebElement;
	}

	public WebElement saveButtonIsPresent(WebDriver driver) {
		WebElement saveButtonWebElement = driver.findElement(saveButton);
		return saveButtonWebElement;
	}

	public WebElement okButtonIsPresent(WebDriver driver) {
		WebElement okButtonWebElement = driver.findElement(okButton);
		return okButtonWebElement;
	}

	public WebElement printInvoiceButtonIsPresent(WebDriver driver) {
		WebElement printInvoiceButtonWebElement = driver.findElement(printInvoiceButton);
		return printInvoiceButtonWebElement;
	}

	public WebElement addNewOTButtonIsPresent(WebDriver driver) {
		WebElement addNewOTButtonWebElement = driver.findElement(addNewOtButtonElement);
		return addNewOTButtonWebElement;
	}

	public WebElement addOtherChargesButtonIsPresent(WebDriver driver) {
		WebElement addOtherChargesButtonWebElement = driver.findElement(addOtherChargesButton);
		return addOtherChargesButtonWebElement;
	}

	//Social Service 

	public WebElement editInformationOfTextFieldIsPresent(WebDriver driver) {
		WebElement editInformationOfTextFieldWebElement = driver.findElement(editInformationOfTextField);
		userActions.highlightElementAfterAction(editInformationOfTextFieldWebElement);
		return editInformationOfTextFieldWebElement;
	}

	public WebElement SSUPatientListButtonIsPresent(WebDriver driver) {
		WebElement editInformationOfTextFieldWebElement = driver.findElement(SSUPatientListButton);
		return editInformationOfTextFieldWebElement;
	}

	public WebElement newSSUPatientRegistrationFormsCloseButtonIsPresent(WebDriver driver) {
		WebElement editInformationOfTextFieldWebElement = driver.findElement(newSSUPatientRegistrationFormsCloseButtonElement);
		userActions.highlightElementAfterAction(editInformationOfTextFieldWebElement);
		return editInformationOfTextFieldWebElement;
	}

	public WebElement verifyPatientInformationTabIsPresent(WebDriver driver) {
		WebElement patientInformationTabWebElement = driver.findElement(patientInformationTabElement);
		userActions.highlightElementAfterAction(patientInformationTabWebElement);
		return patientInformationTabWebElement;
	}

	public WebElement registerNewSSUPatientButtonIsPresent(WebDriver driver) {
		WebElement registerNewSSUPatientButtonWebElement = driver.findElement(registerNewSSUPatientButton);
		return registerNewSSUPatientButtonWebElement;
	}

	public WebElement firstNameTextFieldIsPresent(WebDriver driver) {
		WebElement firstNameTextFieldWebElement = driver.findElement(firstNameTextField);
		return firstNameTextFieldWebElement;
	}

	public String phoneNumberTextFieldIsPresent() throws Exception {
		String phoneNumberTextFieldValue = "";
		try {
			if(userActions.isDisplayed(phoneNumberTextField))
			{
				phoneNumberTextFieldValue = userActions.getAttribute(phoneNumberTextField, "value");
				System.out.println("Contact Number TextField Value  : " + phoneNumberTextFieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return phoneNumberTextFieldValue;
	}

	public String verifyValueIsPresentInLastNameTextbox() throws Exception {
		String lastNameTextFieldValue = "";
		try {
			if(userActions.isDisplayed(lastNameTextField))
			{
				lastNameTextFieldValue = userActions.getAttribute(lastNameTextField, "value");
				System.out.println("Last Name TextField Value  : " + lastNameTextFieldValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return lastNameTextFieldValue;
	}

	public WebElement phoneNumberTextFieldErrorMessageIsPresent(WebDriver driver) {
		WebElement phoneNumberTextFieldErrorMessageWebElement = driver.findElement(phoneNumberTextFieldErrorMessage);
		return phoneNumberTextFieldErrorMessageWebElement;
	}

	public WebElement countryDropdownIsPresent(WebDriver driver) {
		WebElement countryDropdownWebElement = driver.findElement(countryDropdown);
		return countryDropdownWebElement;
	}

	public WebElement verifyCertificateNoTextFieldIsPresent(WebDriver driver) {
		WebElement certificateNoTextboxElement = driver.findElement(certificateNoTextbox);
		userActions.highlightElementAfterAction(certificateNoTextboxElement);
		return certificateNoTextboxElement;
	}


	//OperationTheatre module(L1)

	public WebElement printButtonWebElementIsPresent(WebDriver driver) {
		WebElement printButtonWebElement = driver.findElement(printButton);
		return printButtonWebElement;
	}


	public WebElement ssuPatientListTextIsPresent(WebDriver driver) {
		WebElement ssuPatientListTextWebElement = driver.findElement(ssuPatientListText);
		return ssuPatientListTextWebElement;
	}

	public WebElement warningMessageIsPresent(WebDriver driver) {
		WebElement warningMessageWebElement = driver.findElement(warningMessage);
		return warningMessageWebElement;
	}

	public WebElement hasTG_certificateDropdownIsPresent(WebDriver driver) {
		WebElement hasTG_certificateDropdownWebElement = driver.findElement(hasTG_certificateDropdown);
		return hasTG_certificateDropdownWebElement;
	}

	public WebElement newSsuPatientRegistrationTextFieldIsPresent(WebDriver driver) {
		WebElement newSsuPatientRegistrationTextFieldWebElement = driver.findElement(newSsuPatientRegistrationTextField);
		return newSsuPatientRegistrationTextFieldWebElement;
	}

	public WebElement verifyNewOTBookingButtonIsPresent(WebDriver driver) {
		WebElement NewOtButtonIsPresentWebElement = driver.findElement(newOTBookingButtonByElement);
		return NewOtButtonIsPresentWebElement;
	}

	public WebElement verifyAddNewOTButtonIsPresent(WebDriver driver) {
		WebElement addNewOTButtonWebElement = driver.findElement(addNewOTButtonByElement);
		return addNewOTButtonWebElement;
	}

	public String verifyValueIsPresentInTypeOfSurgeryTextbox() throws Exception {
		String typeOfSurgeryTextboxValue = "";
		try {
			if(userActions.isDisplayed(surgeryTypeTextboxElement))
			{
				typeOfSurgeryTextboxValue = userActions.getAttribute(surgeryTypeTextboxElement, "value");
				System.out.println("type of Surgery Textbox Value  : " + typeOfSurgeryTextboxValue);
			}
		}catch(Exception e) {
			throw e;
		}
		return typeOfSurgeryTextboxValue;
	}

	public WebElement verifySurgenNameFirstButtonElementIsPresent(WebDriver driver) {
		WebElement surgenNameFirstButtonWebElement = driver.findElement(surgenNameFirstButtonElement);
		return surgenNameFirstButtonWebElement;
	}

	public WebElement verifyErrorMessageOfAddExternalReferralForms(WebDriver driver) {
		WebElement errorMessageOfAddExternalReferralFormsWebElement = driver.findElement(errorMessageOfAddExternalReferralFormsByElement);
		return errorMessageOfAddExternalReferralFormsWebElement;
	}

	public WebElement verifyPlusIconElementIsPresent(WebDriver driver) {
		WebElement surgenNameFirstButtonWebElement = driver.findElement(plusIconButtonElement);
		return surgenNameFirstButtonWebElement;
	}


	public String verifySuccessNotificationPopupMessageIsPresent() throws Exception {
		String successNotificationPopupMessageValue = "";
		try {
			if(userActions.isDisplayed(successNotificationPopupMessageElement)) {

				String notificationMessageOne = userActions.getText(successNotificationPopupMessageOneElement);
				System.out.println("notification Message One " + notificationMessageOne);

				String notificationMessageTwo = userActions.getText(successNotificationPopupMessageTwoElement);
				System.out.println("notification Message two " + notificationMessageTwo);

				successNotificationPopupMessageValue = notificationMessageOne + " " +notificationMessageTwo ;
				System.out.println("success notification popup message text Value : " + successNotificationPopupMessageValue );
			}
		}catch(Exception e) {
			throw e;
		}	
		return successNotificationPopupMessageValue;
	}


	//Social service module(L2)

	//	public WebElement saveButtonIsPresent(WebDriver driver) {
	//		WebElement saveButtonWebElement = driver.findElement(saveButton);
	//		return saveButtonWebElement;
	//	}
	//	

	public WebElement submitButtonIsPresent(WebDriver driver) {
		WebElement submitButtonWebElement = driver.findElement(submitButton);
		return submitButtonWebElement;
	}

	public WebElement accountOptionIsSelected(WebDriver driver) {
		WebElement accountOptionWebElement = driver.findElement(accountOption);
		return accountOptionWebElement;
	}

	public Boolean vatInPercentageTextboxIsPresent() throws Exception {
		Boolean vatInPercentageTextboxIsPresent = false;
		try {
			if(userActions.isDisplayed(vatInPercentageTextFieldElement))
			{
				WebElement vatInPercentageTextFieldElementWebElement = userActions.findElement(vatInPercentageTextFieldElement);
				userActions.highlightElement(vatInPercentageTextFieldElementWebElement);
				Thread.sleep(2000);
				vatInPercentageTextboxIsPresent = true;
			}
		}catch(Exception e) {
			throw e;
		}
		return vatInPercentageTextboxIsPresent;
	}

	public Boolean verifyDaysRadioButtonIsSelected() throws Exception {
		Boolean verifyRadioButton = false;
		try {
			Thread.sleep(3000);

			if(userActions.isSelected(daysRadioButtonByElement)) {
				System.out.println("Days Radio button is selected");
				verifyRadioButton = true;
			}	
		}catch(Exception e) {
			throw e;
		}	
		return verifyRadioButton;
	}

	public String verifyFollowUpPatientIsSelected() throws Exception {
		String selectedOptionValue = "";
		try {
			if(userActions.isDisplayed(selectVisitTypeDropdownByElement))
			{
				selectedOptionValue = userActions.getFirstSelectedOptionFromDropdown(selectVisitTypeDropdownByElement, "elementName", "pageName");
				System.out.println("selected dropdown value is  : " + selectedOptionValue);

				return selectedOptionValue;
			}
		}catch(Exception e) {
			throw e;
		}
		return selectedOptionValue;
	}

	public WebElement verifyHandoverToUserButtonIsPresent(WebDriver driver) {
		WebElement handOverToUserButtonOfBillingSubModuleWebElement = driver.findElement(handOverToUserButtonOfBillingSubModuleByElement);
		return handOverToUserButtonOfBillingSubModuleWebElement;
	}

	public String verifyValueIsPresentInTextbox() throws Exception {
		String valueOfTextbox = "";
		try {
			valueOfTextbox = userActions.getAttribute(remarksTextFieldOfBillingSubModulesByElement,"value");
			System.out.println("value of Texbox : " + valueOfTextbox);
			return valueOfTextbox;
		}catch(Exception e) {
			throw e;
		}
	}

	public String verifyTitleNameOfVaccinationPatientRegisterFormIsPresent() throws Exception {
		String titleNameOfVaccinationPatientRegisterForm = "";
		try {
			if (userActions.isDisplayed(titleNameOfVaccinationPatientRegisterFormByElement)) {
				titleNameOfVaccinationPatientRegisterForm = userActions.getText(titleNameOfVaccinationPatientRegisterFormByElement);
				System.out.println("title Name Of Vaccination Patient Register Form : " + titleNameOfVaccinationPatientRegisterForm);
				return titleNameOfVaccinationPatientRegisterForm;
			}
		}catch(Exception e) {
			throw e;
		}
		return titleNameOfVaccinationPatientRegisterForm;
	}

	public String verifyErrorNotificationPopupMessageIsPresent() throws Exception {
		String mainErrorNotificationPopupMessageValue = "";
		try {
			if(userActions.isDisplayed(errorNotificationPopupMessageElement)) {

				String statusOfErrorMessage = userActions.getText(statusErrorNotificationMessageByElement);
				System.out.println("status error notification Message :  " + statusOfErrorMessage);

				String mainNotificationErrorMessage = userActions.getText(mainErrorNotificationMessageByElement);
				System.out.println("main notification Error Message :  " + mainNotificationErrorMessage);

				mainErrorNotificationPopupMessageValue = statusOfErrorMessage + " " + mainNotificationErrorMessage ;
				System.out.println("main error notification popup message text Value : " + mainErrorNotificationPopupMessageValue );

				return mainErrorNotificationPopupMessageValue;

			}
		}catch(Exception e) {
			throw e;
		}	
		return mainErrorNotificationPopupMessageValue;
	}
	
	public String verifySuggestionsIsSelected() throws Exception {
		String selectedSuggestionValue = "";
		try {
			if(userActions.isDisplayed(selectOTAssistantNameDropdownByElement)) {
				selectedSuggestionValue = userActions.getText(selectOTAssistantNameDropdownByElement);
				System.out.println("selected suggestion value is : " + selectedSuggestionValue);
				return selectedSuggestionValue;
			}
		}catch(Exception e) {
			throw e;
		}
		return selectedSuggestionValue;
	}
	
	public WebElement verifyDiscardButtonIsPresent(WebDriver driver) {
		WebElement discardButtonWebElement = driver.findElement(discardButtonByElement);
		return discardButtonWebElement;
	}

}