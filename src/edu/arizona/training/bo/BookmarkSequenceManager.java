package edu.arizona.training.bo;

import java.util.List;

public class BookmarkSequenceManager {

	public static int getNewBookmarkId(List<Bookmark> bookmarks) {
		
		int biggestId = 0;
		for (Bookmark bookmark : bookmarks) {
			if (bookmark.getId() > biggestId) {
				biggestId = bookmark.getId();
			}
		}
		
		return biggestId + 1;
	}
	
}
