package com.sonnyproject.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.Widget;
import com.sonnyproject.client.contactpanel.ContactPanel;
import com.sonnyproject.client.homepanel.HomePanel;
import com.sonnyproject.client.insertpanel.InsertPanel;
import com.sonnyproject.client.loginpanel.LoginPanel;
import com.sonnyproject.client.requestpanel.RequestPanel;
import com.sonnyproject.client.searchpanel.SearchPanel;
import com.sonnyproject.client.userconfigpanel.UserConfigPanel;

public class MovieCollectionPanel extends Composite{

	private static MovieCollectionUiBinder uiBinder = GWT.create(MovieCollectionUiBinder.class);
	interface MovieCollectionUiBinder extends UiBinder<Widget, MovieCollectionPanel>{}
	
	//logo
	interface Resources extends ClientBundle{
		@Source("images/MovieCollezioneLogo.png") public ImageResource logo();
	}
	
	private HomePanel homePanel;
	private SearchPanel searchPanel;
	private InsertPanel insertPanel;
	private RequestPanel requestPanel;
	private ContactPanel contactPanel;
	private LoginPanel loginPanel;
	private UserConfigPanel userConfigPanel;
	
	@UiField HTMLPanel mainArea;
	
	public MovieCollectionPanel(){
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		Window.addResizeHandler(resizeHandler);
		homePanel = new HomePanel();
		setWidgetAsActive(homePanel);
	}
	
	@UiHandler("home")
	void showHomePage(ClickEvent event) {
		History.newItem(HistoryTokens.HOME);
	}
	
	void showHomePage(){
		if (homePanel == null) {
			homePanel = new HomePanel();
		}
		setWidgetAsActive(homePanel);
	}

	@UiHandler("search")
	void showSearchPage(ClickEvent event) {
		History.newItem(HistoryTokens.SEARCH);
	}
	
	void showSearchPage() {
		if (searchPanel == null) {
			searchPanel = new SearchPanel();
		}
		setWidgetAsActive(searchPanel);
	}

	@UiHandler("insert")
	void showInsertPage(ClickEvent event) {
		History.newItem(HistoryTokens.INSERT);		
	}
	
	void showInsertPage() {
		if (insertPanel == null) {
			insertPanel = new InsertPanel();
		}
		setWidgetAsActive(insertPanel);
	}

	@UiHandler("request")
	void showRequestPage(ClickEvent event) {
		History.newItem(HistoryTokens.REQUEST);
	}
	
	void showRequestPage() {
		if (requestPanel == null) {
			requestPanel = new RequestPanel();
		}
		setWidgetAsActive(requestPanel);
	}

	@UiHandler("contact")
	void showContactPage(ClickEvent event) {
		History.newItem(HistoryTokens.CONTACT);
	}
	
	void showContactPage() {
		if (contactPanel == null){
			contactPanel = new ContactPanel();
		}
		setWidgetAsActive(contactPanel);
	}

	@UiHandler("login")
	void showLoginPage(ClickEvent event) {
		History.newItem(HistoryTokens.LOGIN);
	}
	void showLoginPage() {
		if (loginPanel == null) {
			loginPanel = new LoginPanel();
		}
		setWidgetAsActive(loginPanel);
	}

	@UiHandler("user")
	void showUserConfigPage(ClickEvent event){
		History.newItem(HistoryTokens.USER);
	}
	
	void showUserConfigPage() {
		if (userConfigPanel == null) {
			userConfigPanel = new UserConfigPanel();
		}
		setWidgetAsActive(userConfigPanel);
	}

	private void setWidgetToMaxWidthAndHeight () {
        setWidth("100%");
        setHeight(Window.getClientHeight() + "px");
    }
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
        public void onResize (ResizeEvent event) {
            setWidgetToMaxWidthAndHeight();
        }
    };
    
    private void setWidgetAsActive (Widget widget)
    {
    	mainArea.clear();
    	mainArea.add(widget);
    }
}
