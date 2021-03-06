var AppRouter = Backbone.Router.extend({

    routes: {
        "/rest/locations"   : "list",
        "items/page/:page"  : "list",
        "items/add"         : "addItem",
        "items/:id"         : "itemDetails",
        "about"             : "about"
    },

    initialize: function () {
        this.headerView = new HeaderView();
        $('.header').html(this.headerView.el);
    },

	list: function(page) {
        var p = page ? parseInt(page, 10) : 1;
        var itemList = new ItemCollection();
        itemList.fetch({success: function(){
            $("#content").html(new ItemListView({model: itemList, page: p}).el);
        }});
        this.headerView.selectMenuItem('home-menu');
    },

    itemDetails: function (id) {
        var item = new Item({id: id});
        item.fetch({success: function(){
            $("#content").html(new ItemView({model: item}).el);
        }});
        this.headerView.selectMenuItem();
    },

	addItem: function() {
        var item = new Item();
        $('#content').html(new ItemView({model: item}).el);
        this.headerView.selectMenuItem('add-menu');
	},
 
    about: function () {
        if (!this.aboutView) {
            this.aboutView = new AboutView();
        }
        $('#content').html(this.aboutView.el);
        this.headerView.selectMenuItem('about-menu');
    }

});

utils.loadTemplate(['HeaderView', 'LocationView', 'LocationListItemView', 'AboutView'], function() {
    app = new AppRouter();
    Backbone.history.start();
});