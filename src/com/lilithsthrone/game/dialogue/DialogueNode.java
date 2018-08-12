package com.lilithsthrone.game.dialogue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Funtacles
 */
public class DialogueNode {

	private String author, content, headerContent, label;
	private DialogueNodeType type;
	private int minutesPassed;
	private List<DialogueResponse> responses;

	public DialogueNode(String label, String content, DialogueNodeType type, String author, int minutesPassed) {
		this.label = label;
		this.author = author;
		this.content = content;
		this.type = type;
		this.minutesPassed = minutesPassed;

		this.responses = new ArrayList<DialogueResponse>();
	}

	public void addResponse(DialogueResponse response){
		this.responses.add(response.getIndex(), response);
	}

	/**
	 * Dialogue node advances time by this amound of minutes.
	 */
	public int getMinutesPassed(){
		return this.minutesPassed;
	}

	/**
	 * Content that is set above the main content. It is not affected by fade-in
	 * effects.
	 */
	public String getHeaderContent() {
		return this.headerContent;
	}

	/**
	 * The main content for this DialogueNode. It is affected by fade-in effects.
	 */
	public String getContent() {
        return this.content;
    }

	public String getLabel() {
		return label;
	}

	public DialogueNodeType getType() {
		return type;
	}
	
	public DialogueResponse getResponse(int index) {
		return responses.get(index);
	}
	
	public String getAuthor(){
		return this.author;
	}

	@Override
	public String toString() {
		return label;
	}
}
