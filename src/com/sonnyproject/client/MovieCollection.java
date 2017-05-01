package com.sonnyproject.client;

import com.google.gwt.core.client.EntryPoint;
/*import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.History;*/
import com.google.gwt.user.client.ui.RootPanel;

public class MovieCollection implements EntryPoint{

	/*MovieCollectionPanel mainPanel = new MovieCollectionPanel();*/
	
	/**
	 * Entry Point. Add a mainPanel to the RootPanel, and handle any history token that might
	 * have been present when application started.
	 */
    public void onModuleLoad()
    {
        RootPanel.get().add(new MovieCollectionPanel(), 0, 0);
    	//setUpHistoryManagement();
    }
    
    /**
     * Register this class as handling history events, and check to see if we already have to do that.
     */
	/*public void setUpHistoryManagement(){
		// Make this class your history manager (see onValueChange method)
		History.addValueChangeHandler(this);
		// Handle any existing history token
		History.fireCurrentHistoryState();
	}*/
	
	/**
	 * Handle any change to the history.
	 * 
	 * If there is a change to the history, then a ValueChangeEvent is fired.  This method handles that
	 * by parsing the found token, and based on the value, it can request the examplePanel shows the
	 * History example, Layout example, or the introduction screen.
	 * 
	 */
	/*public void onValueChange(ValueChangeEvent<String> event) {
		// Get the token from the event
		String page = event.getValue().trim();
		// Check if the token is null or empty
		if ((page == null) || (page.equals(""))){
			mainPanel.showHomePage();
		}
		else if (page.equals(HistoryTokens.HOME)){
			mainPanel.showHomePage();
		}
		else if (page.equals(HistoryTokens.SEARCH)){
			mainPanel.showSearchPage();
		}			
		else if (page.equals(HistoryTokens.INSERT)){
			mainPanel.showInsertPage();		
		}
		else if (page.equals(HistoryTokens.LOGIN)){
			mainPanel.showLoginPage();
		}
		else{
			mainPanel.showHomePage();
		}
	}*/
}
