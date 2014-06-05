package edu.arizona.training.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import edu.arizona.training.bo.Bookmark;
import edu.arizona.training.bo.BookmarkSequenceManager;

public class BookmarkAction extends DispatchAction {

	private static final String KEYNAME = "bookmarks";
	private static final String FORWARD_DISPLAY = "display";
	
	public ActionForward display(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// we do this here just so a new set can be instantiated if hitting this for the first time
		List<Bookmark> bookmarks = getBookmarks(request, response);
		request.setAttribute("bookmarks", bookmarks);
		return mapping.findForward(FORWARD_DISPLAY);
	}

	public ActionForward edit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idToEdit = Integer.parseInt(request.getParameter("id"));
		List<Bookmark> bookmarks = getBookmarks(request, response);
		Bookmark bookmarkToEdit = findBookmarkById(idToEdit, bookmarks);
		request.setAttribute("bookmarkToEdit", bookmarkToEdit);
		return mapping.findForward("edit");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		int idToDelete = Integer.parseInt(request.getParameter("id"));
		List<Bookmark> bookmarks = getBookmarks(request, response);
		Bookmark bookmarkToDelete = findBookmarkById(idToDelete, bookmarks);
		request.setAttribute("bookmarkToDelete", bookmarkToDelete);
		return mapping.findForward("delete");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("add");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookmarkForm bForm = (BookmarkForm) form;
		
		List<Bookmark> bookmarks = getBookmarks(request, response);
		
		String action = bForm.getAction();
		if ("add".equalsIgnoreCase(action)) {
			Bookmark newBookmark = new Bookmark();
			newBookmark.setId(BookmarkSequenceManager.getNewBookmarkId(bookmarks));
			newBookmark.setName(bForm.getName());
			newBookmark.setUrl(bForm.getUrl());
			bookmarks.add(newBookmark);
		}
		
		if ("edit".equalsIgnoreCase(action)) {
			int bookmarkIdToEdit = Integer.parseInt(bForm.getId());
			Bookmark bookmarkToEdit = findBookmarkById(bookmarkIdToEdit, bookmarks);
			bookmarkToEdit.setName(bForm.getName());
			bookmarkToEdit.setUrl(bForm.getUrl());
		}

		if ("delete".equalsIgnoreCase(action)) {
			int bookmarkIdToDelete = Integer.parseInt(bForm.getId());
			Bookmark bookmarkToDelete = findBookmarkById(bookmarkIdToDelete, bookmarks);
			bookmarks.remove(bookmarkToDelete);
		}
		
		saveBookmarks(bookmarks, request, response);
		bForm.reset(mapping, request);
		
		response.sendRedirect("bookmarks.do?methodToCall=display");
		return null;
	}

	@Override
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.sendRedirect("bookmarks.do?methodToCall=display");
		return null;
	}

	protected void saveBookmarks(List<Bookmark> bookmarks, HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setAttribute(KEYNAME, bookmarks);
	}
	
	protected List<Bookmark> getBookmarks(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		if ( session.getAttribute(KEYNAME) == null ) {
			List<Bookmark> bookmarks = new ArrayList<Bookmark>();
			bookmarks.add(new Bookmark(1, "Google","http://www.google.com"));
			bookmarks.add(new Bookmark(2, "Arizona","http://www.arizona.edu"));
			session.setAttribute(KEYNAME, bookmarks);
		}
		return (List<Bookmark>) session.getAttribute(KEYNAME);
	}
	
	protected Bookmark findBookmarkById(int id, List<Bookmark> bookmarks) {
		for (Bookmark bookmark : bookmarks) {
			if (id == bookmark.getId()) {
				return bookmark;
			}
		}
		return null;
	}
	
}
