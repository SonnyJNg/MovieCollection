package com.sonnyproject.client.contactpanel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sonnyproject.client.widget.CommentField;
import com.sonnyproject.client.widget.DataField;

public class ContactPanel extends Composite{
	private static ContactPanelUiBinder uiBinder = GWT.create(ContactPanelUiBinder.class);	
	interface ContactPanelUiBinder extends UiBinder<Widget, ContactPanel> {}
	
	@UiField HTMLPanel scrollPanel;
	
	@UiField(provided=true) CommentField comment;
	@UiField(provided=true) DataField name;
	@UiField(provided=true) DataField email;	
	
	public ContactPanel() {
		comment = new CommentField("Comments");
		name = new DataField("Name");
		email = new DataField("Email Address:");
		initWidget(uiBinder.createAndBindUi(this));
		Window.addResizeHandler(resizeHandler);
	}
	
	private ResizeHandler resizeHandler = new ResizeHandler() {
		
		public void onResize(ResizeEvent event) {
			scrollPanel.setHeight((event.getHeight() - 50) + "px");
		}
	};
}
