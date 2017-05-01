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
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.sonnyproject.client.homepanel.HomePanel;
import com.sonnyproject.client.insertpanel.InsertPanel;
import com.sonnyproject.client.loginpanel.LoginPanel;
import com.sonnyproject.client.searchpanel.SearchPanel;

public class MovieCollectionPanel extends Composite {

	private static MovieCollectionUiBinder uiBinder = GWT.create(MovieCollectionUiBinder.class);

	interface MovieCollectionUiBinder extends UiBinder<Widget, MovieCollectionPanel> {
	}

	// logo
	interface Resources extends ClientBundle {
		@Source("images/MovieCollezioneLogo.png")
		public ImageResource logo();
	}

	private HomePanel homePanel = new HomePanel();
	private SearchPanel searchPanel = new SearchPanel();
	private InsertPanel insertPanel = new InsertPanel();
	private LoginPanel loginPanel;

	@UiField
	HTMLPanel mainArea;
	
	@UiField
	Button insert;

	@UiField
	Button login;

	@UiField
	Label welcome;

	public MovieCollectionPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		setWidgetToMaxWidthAndHeight();
		Window.addResizeHandler(resizeHandler);
		setWidgetAsActive(new HomePanel());
		insert.setVisible(false);
	}

	@UiHandler("home")
	void showHomePage(ClickEvent event) {
		setWidgetAsActive(homePanel);
	}

	@UiHandler("search")
	void showSearchPage(ClickEvent event) {
		setWidgetAsActive(searchPanel);
	}

	@UiHandler("insert")
	void showInsertPage(ClickEvent event) {
		setWidgetAsActive(insertPanel);
	}

	@UiHandler("login")
	void showLoginPage(ClickEvent event) {
		if (loginPanel == null) {
			loginPanel = new LoginPanel(insert, welcome, login);
		}
		loginPanel.resetLoginPanel();
		if (login.getText().equals("Logout")) {
			loginPanel.logoutClicked();
		} else {
			loginPanel.loginClicked();
		}
	}

	private void setWidgetToMaxWidthAndHeight() {
		setWidth("100%");
		setHeight(Window.getClientHeight() + "px");
	}

	private ResizeHandler resizeHandler = new ResizeHandler() {
		public void onResize(ResizeEvent event) {
			setWidgetToMaxWidthAndHeight();
		}
	};

	private void setWidgetAsActive(Widget widget) {
		mainArea.clear();
		mainArea.add(widget);
	}
}
