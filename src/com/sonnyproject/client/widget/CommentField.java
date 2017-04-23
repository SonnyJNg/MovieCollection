package com.sonnyproject.client.widget;

/**
 * Code taken from book GWT In Action 2nd Edition by Manning
 */

import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.i18n.client.HasDirection;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.TextArea;

/**
 * 
 * Widget that demonstrates how to build a GWT Composite.
 * 
 * It consists of a Label (question) and a TextBox (answer) in a
 * HorizontalPanel.
 * 
 */
public class CommentField extends Composite implements HasText, HasDirection {

	// The UI elements we will manipulate
	InlineLabel label;
	TextArea textarea;
	FlowPanel panel;

	protected Direction dir = Direction.DEFAULT;

	/**
	 * Construct the widget
	 */
	public CommentField(String question) {
		panel = new FlowPanel();
		label = new InlineLabel(question);
		textarea = new TextArea();
		buildDisplay();
		
		panel.addStyleName("WidgetField_panel");
		label.addStyleName("WidgetField_question");
		textarea.addStyleName("WidgetField_answer");

		initWidget(panel);
	}

	private void buildDisplay() {
		panel.clear();
		label.setText(label.getText(), dir);
		textarea.setDirection(dir);
		if (dir.equals(Direction.RTL)) {
			panel.add(textarea);
			panel.add(label);
		} else {
			panel.add(label);
			panel.add(textarea);
		}
	}

	// Utility method to get the answer text
	public String getText() {
		String answer = "";
		if (textarea != null)
			answer = textarea.getText();
		return answer;
	}

	// Utility method to get the question text
	public String getQuestion() {
		String question = "";
		if (label != null)
			question = label.getText();
		return question;
	}

	// Utility method to set the answer text
	public void setText(String text) {
		if (textarea != null)
			textarea.setText(text);
	}

	// Utility method to set the question text
	public void setQuestion(String text) {
		if (label != null)
			label.setText(text);
	}

	public void setDirection(Direction direction) {
		this.dir = direction;
		buildDisplay();
	}

	public Direction getDirection() {
		return dir;
	}

	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<String> handler) {
		return textarea.addValueChangeHandler(handler);
	}
}
