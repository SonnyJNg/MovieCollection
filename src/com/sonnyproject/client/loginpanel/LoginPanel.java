package com.sonnyproject.client.loginpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class LoginPanel extends Composite{
	private static LoginPanelUiBinder uiBinder = GWT.create(LoginPanelUiBinder.class);
	
	interface LoginPanelUiBinder extends UiBinder<Widget, LoginPanel> {}
	
	@UiField ScrollPanel scrollPanel;
	
	public LoginPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
	}
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		
		public void onResize(ResizeEvent event) {
			scrollPanel.setHeight((event.getHeight() - 50) + "px");
		}
	};
}
