package com.sonnyproject.client.userconfigpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class UserConfigPanel extends Composite{
	private static UserCofigPanelUiBinder uiBinder = GWT.create(UserCofigPanelUiBinder.class);
	
	interface UserCofigPanelUiBinder extends UiBinder<Widget, UserConfigPanel> {}
	
	@UiField ScrollPanel scrollPanel;
	
	public UserConfigPanel() {
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
	}
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		
		public void onResize(ResizeEvent event) {
			scrollPanel.setHeight((event.getHeight() - 50) + "px");
		}
	};
}
