package com.sonnyproject.client.loginpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class LoginPanel extends PopupPanel {

	interface MyStyle extends CssResource {
		String hidden();

		String borderEmpty();

		String borderOk();

		String borderError();
	}

	interface LoginPanelUiBinder extends UiBinder<Widget, LoginPanel> {
	}

	private static LoginPanelUiBinder uiBinder = GWT.create(LoginPanelUiBinder.class);

	@UiField
	Button btnLogin;
	@UiField
	Button btnCancel;
	@UiField
	TextBox txtUserName;
	@UiField(provided = true)
	TextBox txtPassword;
	@UiField
	MyStyle myStyle;

	private Boolean userIsLogged;
	Label welcomeMessage;
	Button login;
	Button insertMovie;

	public LoginPanel() {
		userIsLogged = false;
		txtPassword = new PasswordTextBox();
		add(uiBinder.createAndBindUi(this));
		centerPopupOnPage();
		Window.addResizeHandler(repositionOnResize);
	}

	public LoginPanel(Button insertMovie, Label welcomeMessage, Button login) {
		this.insertMovie = insertMovie;
		this.welcomeMessage = welcomeMessage;
		this.login = login;		
		txtUserName = new TextBox();
		txtPassword = new PasswordTextBox();		 
		resetLoginPanel();
		add(uiBinder.createAndBindUi(this));
		centerPopupOnPage();
		Window.addResizeHandler(repositionOnResize);
	}

	public void resetLoginPanel() {
		userIsLogged = false;
		txtUserName.setText("");
		txtPassword.setText("");
		login.setText("Login");
		welcomeMessage.setText("Hello Guest!");
		insertMovie.setVisible(false);
	}

	private boolean validatePassword() {
		return txtPassword.getText().equals("Hala Madrid");
	}

	private boolean validateUserName() {
		return txtUserName.getText().equals("Sonny");
	}

	@UiHandler("btnLogin")
	void submitLoginForm(ClickEvent event) {
		if (validateUserName() && validatePassword()) {
			userLogsIn();
		} else {
			Window.alert("Error entering credentials: " + txtUserName.getText() + " - " + txtPassword.getText());
		}
	}

	@UiHandler("btnCancel")
	void submitCancelForm(ClickEvent event) {
		resetLoginPanel();
		this.hide();
	}

	private ResizeHandler repositionOnResize = new ResizeHandler() {
		public void onResize(ResizeEvent event) {
			centerPopupOnPage();
		}
	};

	private void centerPopupOnPage() {
		int minOffset = 10; // px
		int knownDialogWidth = 400; // this is in the CSS
		int heightAboveCenter = 200; // will set the top to 200px above center

		int left = Math.max(minOffset, (Window.getClientWidth() / 2) - (knownDialogWidth / 2));
		int top = Math.max(minOffset, (Window.getClientHeight() / 2) - heightAboveCenter);
		setPopupPosition(left, top);
	}

	public Boolean isUserLogged() {
		return userIsLogged;
	}

	public void loginClicked() {
		this.show();
	}

	public void logoutClicked() {
		this.hide();
	}

	private void userLogsIn() {
		userIsLogged = true;
		login.setText("Logout");
		welcomeMessage.setText("Hello Boss!");
		insertMovie.setVisible(true);
		this.hide();
	}
}
