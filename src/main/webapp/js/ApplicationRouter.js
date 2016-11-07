var ApplicationRouter = Backbone.Router.extend({
	initialize: function(el) {
		this.el = el;
		this.oneView = new ContentView({template: '#one'});
		this.twoView = new ContentView({template: '#two'});
		this.threeView = new ContentView({template: '#three'});
		this.notFoundView = new ContentView({template: '#not-found'});
	},
	routes: {
		"": "one",
		"one": "one",
		"two": "two",
		"three": "three",
		"else": "notFound"
	},
	currentView: null,
	switchView: function(view) {
		if (this.currentView) {
			// Detach the old view
			this.currentView.remove();
		}
		// Move the view element into the DOM (replacing the old content)
		this.el.html(view.el);
		// Render view after it is in the DOM (styles are applied)
		view.render();
		this.currentView = view;
	},
	/*
	 * Change the active element in the topbar
	 */
	setActiveEntry: function(url) {
		// Unmark all entries
		$('li').removeClass('active');
		// Mark active entry
		$("li a[href='" + url + "']").parents('li').addClass('active');
	},
	one: function() {
		this.switchView(this.oneView);
		this.setActiveEntry('#one');
	},
	two: function() {
		this.switchView(this.twoView);
		this.setActiveEntry('#two');
	},
	three: function() {
		this.switchView(this.threeView);
		this.setActiveEntry('#three');
	},
	notFound: function() {
		this.switchView(this.notFoundView);
	}
});