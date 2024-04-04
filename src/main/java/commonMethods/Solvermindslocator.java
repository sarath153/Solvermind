package commonMethods;

public interface Solvermindslocator {

	public static String username = "username>//input[@id='outlined-size-normal']";
	public static String password = "paswords>//input[@id='outlined-adornment-password']";
	public static String login = "login>//span[text()='Login']";
	public static String selectvessel = "selectvessel>//input[@id='demo-simple-select-outlined']";
	public static String clickvessel = "clickvessel>//*[@id='demo-simple-select-outlined-option-0']";
	public static String clickvessel2 = "clickvessel2>//*[@id='demo-simple-select-outlined-option-1']";
	public static String clicktorrontovessel = "clickvessel>//*[@id='demo-simple-select-outlined-option-276']";
	public static String Allbayplan = "allbay>//*[@alt='06-bayPlan']";

	public static String schedule = "schedule>//img[@alt='04-SheduleVoyage']";

	public static String newschedule = "newschedule>(//*[@class='image_size '])[1]";
	public static String stow_zoomin = "stow_zoomin>//*[@id='6']/div/div/img[1]";
	public static String canvas6 = "canvas6>(//*[@class='MuiCardContent-root'])[6]";
	public static String searchservice = "searchserivice>(//*[@src='assets/images/common-icons/search.svg'])[1]";
	public static String toronto = "expressname>//*[text()='Toronto Express']";
	public static String voyagefrom = "voyagefrom>(//*[text()='Voyage From']/following::input)[1]";
	public static String voyageto = "voyageto>(//*[text()='Voyage From']/following::input)[2]";

	public static String boundfrom = "boundfrom>//*[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense']";
	public static String selectboundS = "selectboundS>//*[@data-value='S']";
	public static String boundto = "boundto>(//*[@class='MuiSelect-root MuiSelect-select MuiSelect-selectMenu MuiSelect-outlined MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputMarginDense MuiOutlinedInput-inputMarginDense'])[2]";
	public static String selectboundW = "selectboundW>//*[@data-value='W']";
	public static String Show = "show>//span[text()='Show']";
	public static String portsearch = "searchport>//*[text()=' Schedule Details ']/following::img]";
	public static String selectport = "selectport>//*[text()='SOGESTER']";
	public static String add = "add>//*[text()='Add']";
	public static String Planpattern = "Planpattern>//li[text()='Plan Pattern']";
	public static String save = "save>//*[@src='assets/images/Mastertoolbar-icons/Save.svg']";
	public static String optimiser = "optimiser>//*[@alt='Optimizer']";
	public static String crane = "crane>//*[@src='/assets/images/optimizer-icons/active/icon-Optimizr-Cranes.svg']";
	public static String solvecrane = "solvecrane>(//*[@class='MuiButtonBase-root MuiIconButton-root'])[3]";
	public static String semiauto = "semiauto>//span[text()='Semi Auto Slot']";

	public static String stow_table = "stow_table>//*[@id='5']";
	public static String stow_table1 = "stow_table1>//*[@id='4']";
	public static String dropdown = "dropdown>//*[@id='demo-simple-select-autowidth']";
	public static String planpatterndropdown = "planpatterndropdown>//*[@id='demo-simple-select-outlined']";
	public static String SelectNone = "SelectNone>//*[@data-value='None']";
	public static String PODPreference = "Podpreference>//*[@data-value='Pod Preference']";

	public static String OutsideCanvas = "OutsideCanvas>//*[@id='AllBayCanvas']";
	public static String canvas_bay_1 = "Bay 01 canvas>(//*[@id='AllBayCanvas']//following::canvas)[1]";
	public static String TwinBayCanvas = "TwinBayCanvas>//*[@id='TwinBayCanvas']//following::canvas";
	public static String stowNo = "StowNumber>//div[contains(text(),'StowNo')]";
	public static String threeD_view = "ThreeD view>//*[@alt='icon_3dview']";
	public static String view_360 = "360 view>//*[@data-tip='Turn off 3D Rotate']";
	public static String switchTo_2D_3D = "Switch to 2D and 3D>//*[contains(@alt,'Switch to ')]";
	public static String bayBackward = "Bay Backward>(//*[text()='Logout']//following::div)[5]";
	public static String bayForward = "Bay Frontward>(//*[text()='Logout']//following::div)[37]";
	public static String baySelect = "Bay Select>(//*[text()='Logout']//following::div)[7]";// (//*[@class='MuiSvgIcon-root
																							// css-1a1893a'])[2]
	public static String containers_WW = "Containers WW>//*[text()='WW']";// *[text()='All Bays']
	public static String containers_WW1 = "Containers WW>(//*[text()='WW'])[1]";
	public static String container_All = "All container>//div[@class='css-1y9z8ki']";
	public static String port = "Port>//*[@id='orientCubeWrapper']";

	public static String containerDetails = "Container Details>//*[text()='Container No :']//following::p";
	public static String firstBay = "First bay>(//*[@class='MuiSvgIcon-root css-1a1893a'])[2]//following::p[text()='01']";

	public static String ThreedPage_canvas = "ThreeDPage Canvas>//*[@id='orientCubeWrapper']//preceding::canvas";
	public static String Add_cargo_close = "Add_cargo_close>//*[text()='X']";

	public static String baychange = "Change bay>(//*[local-name()='svg' and @class='MuiSvgIcon-root MuiSelect-icon']/*[local-name()='path'])[6]";

	// Dashboard
	public static String File = "file>//*[text()='File']";
	public static String Openplan = "Openplan>//span[text()='Open Plan']";
	public static String Globalplan = "Globalplan>//span[text()='Global Open']";
	
	
	public static String Plandescription = "Plandescription>//input[@aria-label='Plan Description Filter Input']";
	public static String clickOk = "clickOk>//*[@type='button']/following::*[text()=' OK']";
	public static String selectTest = "selectTest>//*[contains(text(),'TEST Schedule')]";
	public static String clickyes = "Yes>//span[text()='Yes']";

	
	
	// Optimiser

	public static String PlanOrder1 = "Planorder1>(//*[local-name()='svg' and @class='MuiSvgIcon-root MuiSvgIcon-colorSecondary']/*[local-name()='path'])[4]";
	public static String PlanOrder4 = "Planorder4>(//*[contains(text(),\"Haz Solve\")]/following::span)[1]";
	public static String Containerpool = "Containerpool>//*[@alt='27-ContainerPool']";
	public static String Planpatterncp = "Planpatterncp>//*[text()='Plan Pattern']";

	public static String hanging_alert = "hanging_alert>//*[text()='Hanging Container !']";
	public static String Yes_Btn = "Yes Button>//*[text()='Hanging Container !']//following::span[text()='Yes']";
	public static String plan = "Plan>//li[text()='Plan']";

	public static String ZoomIn_outsidecanvas = "ZoomIn outside canvas>(//*[@id='TwinBayCanvas']//following::button[@aria-label='Zoom In'])[1]";
	public static String ZoomOut_outsidecanvas = "ZoomOut outside canvas>(//*[@id='TwinBayCanvas']//following::button[@aria-label='Zoom In'])[2]";
	public static String resetZoom_outsidecanvas = "ResetZoom outsidecanvas>//*[@id='TwinBayCanvas']//following::button[@aria-label='Reset Zoom']";

	public static String canvas_2 = "canvas2>//*[@id='TwinBayCanvas']//following::canvas";

	public static String dry = "Dry>//span[text()='Dry']";
	public static String refreg = "Refregerator>//span[text()='Rfr']";
	public static String minTemp = "Minimum temperature>(//label[text()='Min Temp']//following::input)[1]";
	public static String maxTemp = "Maximum temperature>(//label[text()='Min Temp']//following::input)[3]";
	public static String avgTemp = "Average Temperature>(//label[text()='Min Temp']//following::input)[2]";

	public static String Overweight = "Overweight container>//*[text()='OverWeight Container']";
	public static String Yes_Btn2 = "Overweight yes btn>//*[text()='OverWeight Container']//following::span[text()='Yes']";
	public static String alert3 = "Alert3>//*[text()='Not Reefer Stows']";
	public static String Yes_Btn3 = "Alert 3 yes>//*[text()='Not Reefer Stows']//following::span[text()='Yes']";

	public static String OpenVessel = "Open vesel>//td[text()=' ALX B170 ']";

	public static String planPattern = "Plan pattern>//li[text()='Plan Pattern']";
	public static String dropdown2 = "dropdown>(//span[text()='Plan Pattern']//following::div)[1]";
	public static String dgpreference = "DG Preference>//li[text()='DG Preference']";

	public static String dropdown4 = "Dropdown 4>(//*[text()='Open Container Pool On: ']//following::div)[3]";
	public static String internalWindow = "Internal Window>//li[text()='Internal Window']";

//	public static String containerPool="Container Pool>(//*[contains(@alt,'bayPlan')]//following::div)[1]";
	public static String newPlanpattern = "Plan pattern new >(//span[text()='Plan Pattern'])[1]";

	public static String minimize2 = "Minimize2>(//div[text()='Container Pool']//following::button)[1]";
	public static String View = "view>//*[@data-value='3']";
	public static String Close = "Close>//span[text()='X']";

	// Scenario1

	public static String VAPPlan_766 = "SelectPlan_766>//*[contains(text(),'VAPPlan-766')]";
	public static String VAPPlan_765 = "SelectPlan_765>//*[contains(text(),'VAPPlan-765')]";
	public static String Openplantop = "OpenPlan_Topbar>//*[@alt='01-OPenPlan']";
	public static String RobRefresh = "Robrefresh>//*[@alt='18-ROBrefresh']";
	public static String Plantemplate = "Templatedropdown>//*[@id='Plan Template']";

	public static String Lashing = "Lashingoption>//*[@value='TTX-AT2-12W03-13W05-E-W-standard-GBSOU-Lashing']";
	public static String VWR = "VWROption>//*[@id='VWR']";
	public static String select2 = "select2.0m>//*[@value='2.0 m']";
	public static String DSWGM = "DSW>(//input[@id='TargetGM'])[1]";
	public static String Run = "Run>//*[text()='Run']";
	public static String Success = "Success>//*[@alt='Partial Success']";
	public static String Starttime = "Starttime>(//*[@id=\"alert-dialog-slide-description\"]/div/div/div/div[1]/text())[2]";
	public static String ClickOk = "Okay>//span[text()='Ok']";
	public static String Search = "Search>//img[@alt='Search']";
	public static String SearchInput = "searchInput>//input[@placeholder='Search']";
	public static String BayMenu = "BayMenu>//*[@id='content']";
	public static String ExportExcel = "ExportMenu>//*[@alt='Export As Excel']";
	public static String Cargolist = "Cargolist>(//*[contains(text(),'Cargo List')])[1]";
	public static String Canvas2 = "Canvas2>//*[@id='7']";

	// Scenario2
	public static String Ok_BTN = "Okay btn after succes>//span[text()='Ok']";
	public static String selectWindow = "Select window > (//*[text()='Open Container Pool On: ']//following::div)[1]";
	public static String CNNB = "CNNB>//span[text()='CNNGB']";
	public static String CNNB2 = "CNNB2>//div[text()='CNNGB']";
	public static String planPattern_ = "PlanPattern>//div[text()='Plan Pattern']";
	public static String Home_Icon = "Home_Icon>//button[@title='Home']";
	public static String Exit_popup = "Exit popup>//*[text()='Do you want to save recent plan?']";
	public static String ExitBtn = "Exist Btn>(//*[text()='Do you want to save recent plan?']//following::button)[1]";
	public static String internal = "internal>//li[@data-value='internal']";
	public static String CanvasFull = "Full Canvas>//div[@id='AllBayCanvas']";
	public static String VAPPlan_763 = "SelectPlan_766>//*[contains(text(),'VAPPlan-763')]";
	public static String VAPPlan_764 = "SelectPlan_766>//*[contains(text(),'VAPPlan-764')]";
	public static String clickCJV = "clickvessel>//li[contains(text(),'CJV - CMA CGM JULES VERNE')]";
	public static String External = "External>//*[text()='External Window']";
	// Canvas Bays
	public static String Bay1 = "SelectPlan_766>//*[@id='1']";

	public static String checkbox = "Checkbox>//*[contains(text(),'Single Screen Plan')]/preceding::input[@type='checkbox']";
	public static String PreviousBay = "Aft>//*[@src='assets/images/common-icons/goPrev.svg']";
	public static String bayClose = "Bay Close >(//div[contains(text(),' / ')]//following::button[@aria-label='Close'])[1]";
	public static String ABP = "ABP>//div[text()='All Bay Plan']";
	public static String select5 = "5.0m>//*[@value='5.0 m']";
	public static String mini = "minimize>//div[text()='Container Pool']//following::button[@aria-label='Minimize']";

	// Scenario4

	public static String click_JYH = "clickvessel>//*[@id='demo-simple-select-outlined-option-143']";
	public static String home_Page = "Home Page >//img[@class='logo-icon']";
	public static String service_Input = "Service Input>//input[@aria-label='Service Code Filter Input']";
	public static String TEST_service = "TEST Service>//*[text()='TEST']";

	public static String servicetable = "Service table >(//span[text()='Voyage No']//following::div[@col-id='voyageno'])[1]";
	public static String portcodeSearch = "Portcode Search>(//span[text()='Port Code']//following::img[@alt='Search'])[1]";
	public static String portcode_Input = "Portcode Input>//input[@aria-label='Port Code Filter Input']";
	public static String INNA_ = "INNA>//div[text()='INNSA']";
	public static String INMAA_ = "INMAA>//div[text()='INMAA']";
	public static String ScheduleTabClose = "Schedule Tab Close >//div[text()='Schedule']//following::button[@aria-label='Close']";

	public static String boundfrom1 = "boundfrom>((//*[text()='Voyage From']/following::input)[2]//following::div[@aria-haspopup='listbox'])[1]";
	public static String boundTo1 = "bound To>((//*[text()='Voyage From']/following::input)[2]//following::div[@aria-haspopup='listbox'])[2]";
	public static String AddCargoTab = "Add Cargo Tab>//h2[text()='Add Cargo']";
	public static String ISOCode = "ISO code input>//input[@id='isoCode']";
	public static String selectISO = "Select ISO >//input[@aria-activedescendant='isoCode-option-0']";
	public static String add_Ok = "Add >//span[text()='Add']";

	public static String selectboundE = "selectboundE>//*[@data-value='E']";

	// Scenario4

	public static String MasterTIRPlan = "MasterTIRPlan>//*[text()='TIR SAS MASTER FILE TD 29063']";
	public static String TesTIRPlan = "TestTIRPlan>//*[text()='TIR TD SAS 2m 3m DSW TEST CASE 29063']";
	public static String Selectlist1 = "Selectcargolist>//*[@id='react-autowhatever-1--item-0']";
	public static String Saveplan = "Saveplan>//*[@alt='02-SavePlan']";
	public static String Globalsave = "Globalsave>//span[text()='Global save']";
	public static String Saveinput = "Saveinput>//h2[text()='Save Plan']/following::input";
	public static String Clickplanok = "Saveinput>//*[@type='button']/following::*[text()='OK']";

	public static String Iframe = "Frame>//*[@class='fa fa-check']";

	public static String ClicksaveOk = "ClicksaveOk>//button[@class='MuiButtonBase-root MuiButton-root MuiButton-text mx-5 p-button-success']";
	public static String click_TIR = "clickTIR>//*[contains(text(),'TIR - TIRUA')]";

	public static String Calculate = "Calculate>//span[text()='Calculate']";

	public static String Masterplan = "clickMasterplan>//*[contains(text(),'Master Plan')]";
	public static String Storageplan = "clickStowageplan>//*[contains(text(),'Stowage Plan')]";

	public static String planOpened = "OpenedPlan>//h4[@class='ves_topbar_align']";
	public static String masterPlan_optimizer = "Master plan in Optimizer >//input[@value='MASTER PLAN']//following::span[text()='Master Plan']";
	public static String masterAndStowage = "Master And Stowage plan checkbox>//h5[text()='Master and Stowage Plan']";
	public static String success = "Success>//img[@alt='Finished']";
	public static String runOk = "Run ok>(//img[@alt='Finished']//following::button)[1]";
	public static String maxArrow = "Max Arrow>//*[@class='MuiSvgIcon-root jss1348']";
	public static String stowRunning = "Stowage Running>//p[contains(text(),'Stowage Plan is Running')]";

	// validation
	public static String getCellCount = "tables >(//div[@ref='rightContainer'])[1]/preceding::div[@aria-colindex and @tabindex='-1']";
	public static String lastRow = "LastRow>(//div[@class='ag-row ag-row-no-focus ag-row-even ag-row-level-0 ag-row-position-absolute ag-row-last'])[2]";// aria-rowindex

	public static String tablevalues = "Table value>(//div[@class='ag-cell ag-cell-not-inline-editing ag-cell-auto-height locked-col ag-cell-value'])[2]";

	public static String INNSA = "INNSA>//span[text()='INNSA']";
	public static String canvasTest = "Canvas test>//div[@id='TwinBayCanvas']";
	public static String countValue = "Count value>(//span[@class='ag-group-value']//following::div)[1]";

	public static String cargoListTab = "Cargo List Tab>//div[text()='CARGOLIST']";
	public static String weightInput = "WeightInput>(//div[@col-id='fWeight'])[2]";
    public static String weightInput2= "weightInput>(//div[@col-id='fWeight']//following::div[@class='ag-react-container'])[1]";
	public static String polInput = "POL Input >((//div[@col-id='fWeight'])[2]//following::div)[1]";
	public static String export = "Export>//button[@name='Export']";
	public static String Rowtype = "Change inner outer>//label[text()='  Row ']//following::div[@id=\"demo-simple-select-outlined\"]";
	public static String outerRow = "Row type Outer>//li[@data-value='Outer']";
	public static String innertRow = "RowtypeInner>//li[@data-value='Inner']";

	public static String meanDraft = "Mean Draft>//input[@id='TargetDraft']";
	public static String closetab3 = "Vessel weight range tab close>(//div[text()='Vessel Weight Range']//following::button[@aria-label='Close'])[1]";
	public static String Bay_ = "Bay element>//div[contains(text(),'Bay ')]";
	public static String WeightTrue = "Weight Visible>//div[contains(text(),'Weight = ')]";

	public static String savePlan = "Save Plan>//*[@alt='02-SavePlan']";
	public static String globalSave = "Global Save>//*[contains(text(),'Global save')]";
	public static String OK_ = "Okay Btn>//span[text()=' OK']";
	public static String planNameInput = "Plan name input>(//label[text()='Plan Description']//following::input)[1]";
	public static String Ok_2 = "Okay button>//span[text()='OK']";
	public static String cargoDetails = "Cargo Details>//*[@alt='09-cargoDetails']";
	public static String CDS = "CDS>//div[text()='Cargo Details']";
	public static String allRadioBtn = "All radio button>//div[text()='Cargo Details']//following::span[text()='All']";
	public static String PolInput = "POL Input >((//div[@col-id='fWeight'])[2]//following::div)[3]";
	public static String portClose = "PortClose>//*[@class='dialog_close']";
	public static String CDS_close = "CDS close>//div[text()='Cargo Details']//following::button[@aria-label='Close']";

	//
	public static String groupValue = "GroupValue>(//span[@class='ag-group-value'])[1]";

	public static String Tankdetails = "clickTankdetails>//img[@title='Tank Details']";

	public static String selectsearch = "clickselectsearch>(//span[contains(text(),'Ballast')])[2]";

	public static String radiobutton = "clickradiobutton>//span[text()='Update BW Quantity']";

	public static String valuesend = "valuesend>(//input[@aria-invalid='false'])[3]";
	public static String Tankexport = "Tankexport>//img[@src='assets/images/Mastertoolbar-icons/Export.svg']";
	public static String Search9 = "Search9>//img[@alt='Search']";
	public static String Run1 = "Run1>//*[text()=' Run']";
	public static String Okbutton = "Okbutton>//button[@id='ok']";
	public static String Exportok = "clickexportok>//span[text()='Ok']";
	
	public static String planIdInput="Plan ID Filter Input>//input[@aria-label='Plan Id Filter Input']";
    public static String planDescription="Plan Description input>//input[@aria-label='Plan Description Filter Input']";
	public static String excelcheck="Export excel check>//button[contains(@aria-describedby,'mui')]";
	

	public static String masterPattern="Master Pattern>//span[text()='Master Pattern']";
	public static String semiAutoPlan="Semi Auto plan>//span[text()='Semi Auto Slot']";
	public static String masterPlanView="Master Plan View>//img[@alt='07-masterPlan']";
	public static String external = "internal>//li[@data-value='external']";

	//Scenario_11
	
	public static String CloseOpenplan = "CloseOpenplan>//div[contains(text(),'Open Plan')]/following::button[@aria-label='Close']";
	public static String Mininmisecargodetails = "Mininmisecargodetails>//*[contains(text(),'Cargo Details')]/following::button[@aria-label='Minimize']";
	public static String Weightinput1 = "WInput>(//*[@row-id='2FC1']/following::input)[2]";
	public static String Weightinput = "Wightinput>(//div[@col-id='fWeight'])[2]";
	public static String ClickALL = "SelectAll>//span[text()='All']";	
	
}
