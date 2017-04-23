package com.sonnyproject.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SimpleHtmlSanitizer;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.ClosingEvent;
import com.google.gwt.user.client.Window.ClosingHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.sonnyproject.client.widget.DataField;
import com.sonnyproject.client.widget.CommentField;

public class MovieCollection implements EntryPoint, ValueChangeHandler<String> {
	/**
	 * Constant values to keep track of current panel
	 */
	static final int TOKEN_HOME = 0;
	static final int TOKEN_SEARCH = 1;
	static final int TOKEN_INSERT = 2;
	static final int TOKEN_REQUEST = 3;
	static final int TOKEN_CONTACT = 4;
	static final int TOKEN_LOGIN = 5;
	static final int TOKEN_USERS = 6;
	/**
	 * Tab content
	 */
	static final String TAB_HOME = "Home";
	static final String TAB_SEARCH = "Search";
	static final String TAB_INSERT = "Insert";
	static final String TAB_REQUEST = "Request";
	static final String TAB_CONTACT = "Contact";
	static final String TAB_LOGIN = "Login";
	static final String TAB_USERS = "Users";
	/**
	 * Association between history token to tab panel index
	 */
	enum Pages {
		HOME(TOKEN_HOME, TAB_HOME), SEARCH(TOKEN_SEARCH, TAB_SEARCH), 
		INSERT(TOKEN_INSERT, TAB_INSERT), REQUEST(TOKEN_REQUEST, TAB_REQUEST),
		CONTACT(TOKEN_CONTACT, TAB_CONTACT), LOGIN(TOKEN_LOGIN, TAB_LOGIN),
		USERS(TOKEN_USERS, TAB_USERS);
		
		private int val;
		private String text;
		
		int getVal(){
			return val;
		}
		
		String getText(){
			return text;
		}
		
		Pages(int val, String text){
			this.val = val;
			this.text = text;
		}
	}
	
	private String getContent(String id){
		String toReturn ="";
		Element element = DOM.getElementById(id);
		if(element != null){			
			toReturn = DOM.getInnerHTML(element);
			DOM.setInnerText(element, "");
			SafeHtml sfHtml = SimpleHtmlSanitizer.sanitizeHtml(toReturn);
			toReturn = sfHtml.asString();
		}
		else{
			toReturn = "Cannot find requested page: " + id;
		}
		
		return toReturn;
	}
	
	@Override
	public void onModuleLoad() {
		setUpGui();
		setUpHistoryHandling();
		setUpEventHandling();
	}

	private void setUpGui() {
		buildTabContent();
		//wrapExistingLoginButton();
		insertLogo();
		//createFeedbackTab();
		styleTabPanelUsingUIObject();
		//styleButtonUsingDOM();
		//RootPanel.get().add(feedback);
		Label headerLabel = new Label("Sonny's Movie Collection");
		RootPanel logoSlot = RootPanel.get("logo");
		if (logoSlot != null){
			logoSlot.add(logo);
			logoSlot.add(headerLabel);
			
		}
		RootPanel contentSlot = RootPanel.get("content");
		if (contentSlot != null){
			contentSlot.add(content);
		}
	}

	HTMLPanel homePanel;
	HTMLPanel searchPanel;
	HTMLPanel insertPanel;
	HTMLPanel requestPanel;
	HTMLPanel contactPanel;
	HTMLPanel loginPanel;
	HTMLPanel usersPanel;
	/**
	 * TabLayoutPanel holds the pages of content
	 */
	TabLayoutPanel content;
	private void buildTabContent() {
		homePanel = new HTMLPanel(getContent(Pages.HOME.getText()));
		searchPanel = new HTMLPanel(getContent(Pages.SEARCH.getText()));
		insertPanel = new HTMLPanel(getContent(Pages.INSERT.getText()));
		requestPanel = new HTMLPanel(getContent(Pages.REQUEST.getText()));
		contactPanel = createContactPanel();
		loginPanel = new HTMLPanel(getContent(Pages.LOGIN.getText()));
		usersPanel = new HTMLPanel(getContent(Pages.USERS.getText()));
		
		String htmlPanel = "htmlPanel";
		homePanel.addStyleName(htmlPanel);;
		searchPanel.addStyleName(htmlPanel);
		insertPanel.addStyleName(htmlPanel);
		requestPanel.addStyleName(htmlPanel);
		contactPanel.addStyleName(htmlPanel);
		loginPanel.addStyleName(htmlPanel);
		usersPanel.addStyleName(htmlPanel);
			
		content = new TabLayoutPanel(30, Unit.PX);
		
		content.add(homePanel, Pages.HOME.getText());
		content.add(searchPanel, Pages.SEARCH.getText());
		content.add(insertPanel, Pages.INSERT.getText());
		content.add(requestPanel, Pages.REQUEST.getText());
		content.add(contactPanel, Pages.CONTACT.getText());
		content.add(loginPanel, Pages.LOGIN.getText());
		content.add(usersPanel, Pages.USERS.getText());
		
		content.selectTab(TOKEN_HOME);
	}

	private HTMLPanel createContactPanel() {
		contactPanel = new HTMLPanel(getContent(Pages.CONTACT.getText()));
		DataField name = new DataField("What is your name?");
		DataField email = new DataField("What is your email address?");
		CommentField comment = new CommentField("Comment: ");
		contactPanel.add(name);
		contactPanel.add(email);
		contactPanel.add(comment);
		return contactPanel;
	}

	/**
	 * Popup panel that allows user to login
	 */
	PopupPanel loginRequest;
	
	/**
	 * Button used for login event trigger
	 */
	/*Button login;
	private void wrapExistingLoginButton() {
		Element elemLogin = DOM.getElementById("Login");
		if(elemLogin != null){
			login = Button.wrap(elemLogin);
		}
		else{
			GWT.log("The login button is missing");
			login = new Button("Login");
			RootPanel.get().add(login);
		}
	}*/

	/**
	 * Image's logo
	 */
	Image logo;
	/**
	 * Logo path location
	 */
	private static final String LOGO_IMAGE_NAME = "MovieCollezioneLogo.png";
	private void insertLogo() {
		logo = new Image(GWT.getModuleBaseURL() + "../Images/" + LOGO_IMAGE_NAME);
	}

	private void styleTabPanelUsingUIObject() {
		// Set up the heights of the pages.
		String height = "400px";
		homePanel.setHeight(height);
		searchPanel.setHeight(height);
		insertPanel.setHeight(height);
		requestPanel.setHeight(height);
		contactPanel.setHeight(height);
		loginPanel.setHeight(height);
		usersPanel.setHeight(height);
		content.setHeight("420px");
	}

	private void setUpHistoryHandling() {
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
		// Trap user hitting back button too many times.
		Window.addWindowClosingHandler(new ClosingHandler(){
			public void onWindowClosing(ClosingEvent event) {
				event.setMessage("You are about to abandon this app. Is it ok to do so?");
			}
		});
	}
	
	/**
	 * Panel sits on the right hand side allows to user feedback
	 */
	/*FocusPanel feedback;
	
	private void createFeedbackTab() {
		// Create the FeedBack tab
		feedback = new FocusPanel();
		feedback.setStyleName("feedback");
		feedback.addStyleName("normal");
		VerticalPanel text = new VerticalPanel();
		text.add(new Label("FEEDBACK"));
		feedback.add(text);
	}*/
	
	private void setUpEventHandling() {
		/**
		 *  If a tab is selected then we want to add a new history item to the History object.
		 *  (this effectively changes the token in the URL, which is detected and handled by 
		 *  GWT's History sub-system.
		 */
		content.addSelectionHandler(new SelectionHandler<Integer>(){
			public void onSelection(SelectionEvent<Integer> event) {
				// Determine the tab that has been selected by interrogating the event object.
				Integer tabSelected = event.getSelectedItem();
				
				// Create a new history item for this tab (using data retrieved from Pages enumeration)
				History.newItem(Pages.values()[tabSelected].getText());
			}
		});
		
		
		/**
		 *  If the login button is clicked, we want to display a little pop-up panel which allows
		 *  the user to login.
		 */ 
		/*login.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				FlowPanel fLogin;
				final TextBox txtUserName = new TextBox();
				final PasswordTextBox txtPassword = new PasswordTextBox();
				
				// If login button is clicked for the first time then the loginRequest Pop-up panel does not yet exist
				// so we'll build it first as follows:
				if (loginRequest==null){
					// Create the PopupPanel widget
					loginRequest = new PopupPanel();
					
					// Create a FlowPanel to hold the user name and password
					fLogin = new FlowPanel();
					// Add a Label to the Flow Panel that represents the "User Name" text
					fLogin.add(new Label("User Name:"));
					// Add the answer TextBox (which we declared above) to the FlowPanel
					fLogin.add(txtUserName);
					
					fLogin.add(new Label("Password:"));
					fLogin.add(txtPassword);
					
					// Add a change handler to the TextBox so that when there is a change to user name term 
					// we would "start" the login
					txtUserName.addChangeHandler(new ChangeHandler(){
						public void onChange(ChangeEvent event) {
							// Hide the popup panel from the screen
							loginRequest.hide();
							// "start" the login
							Window.alert("If implemented, now we would login as: " + txtUserName.getText());
						}
					});

					loginRequest.add(fLogin);
					loginRequest.setAnimationEnabled(true);
					loginRequest.showRelativeTo(login);
					loginRequest.setAutoHideEnabled(true);
					loginRequest.setAutoHideOnHistoryEventsEnabled(true);
				} else {
					// search popup already exists, so clear the TextBox contents...
					txtUserName.setText("");
					txtPassword.setText("");
					// ... and simply show it.
					loginRequest.show();
				}
				
				// Set the TextBox of the popup Panel to have focus - this means that once the pop up is displayed
				// then any keypresses the user makes will appear directly inthe TextBox.  If we didn't do this, then 
				// who knows where the text would appear.
				txtUserName.setFocus(true);
			}			
		});*/
		
		/**
		 * If the user moves mouse over feedback tab, change its style 
		 * (increases its size and changes colour - styles are in BasicProject.css)
		 */
		/*feedback.addMouseOverHandler(new MouseOverHandler(){
			public void onMouseOver(MouseOverEvent event) {
				// Remove existing normal style
				feedback.removeStyleName("normal");
				// Add the active style
				feedback.addStyleName("active");
				// Set overflow of whole HTML page to hidden  to minimise display of scroll bars.
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "hidden");
			}
		});*/
		
		/**
		 * If use moves mouse out of the feedback panel, return its style to normal
		 * (decreases its size and changes colour - styles are in BasicProject.css)
		 */
		/*feedback.addMouseOutHandler(new MouseOutHandler(){
			public void onMouseOut(MouseOutEvent event) {
				feedback.removeStyleName("active");
				feedback.addStyleName("normal");
				RootPanel.getBodyElement().getStyle().setProperty("overflow", "auto");
			}
		});*/
		
		/**
		 * If user clicks on the feedback tab we should start some feedback functionality.
		 * In this example, it simply displays an alert to the user.
		 */
		/*feedback.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent event) {
				Window.alert("You could provide feedback if this was implemented");
			}
		});*/
	}

	public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String page = event.getValue().trim();
		
		if ((page == null) || (page.equals(""))){
			showHomePage();
		}
		else if (page.equals(Pages.HOME.getText())){
			showHomePage();
		}
		else if (page.equals(Pages.SEARCH.getText())){
			showSearch();
		}					
		else if (page.equals(Pages.INSERT.getText())){
			showInsert();
		}
		else if (page.equals(Pages.REQUEST.getText())){
			showRequest();
		}
		else if (page.equals(Pages.CONTACT.getText())){
			showContact();
		}
		else if (page.equals(Pages.LOGIN.getText())){
			showLogin();
		}
		else if (page.equals(Pages.USERS.getText())){
			showUserConfig();
		}
		else{
			showHomePage();
		}
	}

	private void showHomePage() {
		content.selectTab(Pages.HOME.getVal());
	}

	private void showSearch() {
		content.selectTab(Pages.SEARCH.getVal());
	}

	private void showInsert() {
		content.selectTab(Pages.INSERT.getVal());
	}

	private void showRequest() {
		content.selectTab(Pages.REQUEST.getVal());
	}

	private void showContact() {
		content.selectTab(Pages.CONTACT.getVal());
	}

	private void showLogin() {
		content.selectTab(Pages.LOGIN.getVal());
	}
	
	private void showUserConfig(){
		content.selectTab(Pages.USERS.getVal());
	}
}
