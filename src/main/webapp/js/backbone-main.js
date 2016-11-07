// Define Model
window.Location = Backbone.Model.extend({
    urlRoot:"/springmvc-jpa/rest/locations",
    defaults:{
        "id":null,
        "name":"",
        "address":"",
        "phone":"",
        "latitude":"",
        "longitude":"",
        "description":"",
    },
});

//Define Collection
window.LocationCollection = Backbone.Collection.extend({
    model:Location,
	urlRoot: '/springmvc-jpa/rest/locations',
	url: function() {
		console.log('Debug new LocationCollection');
		return this.urlRoot;// + '?name=' + this.id;
	}
});

// Define Views
window.LocationListView = Backbone.View.extend({
   el: $('#locations'),   
   template: _.template($('#locationTemplate').html()),

   render: function (eventName) {
		console.log('Debug new LocationListView');
      _.each(this.model.models, function (location) {
           var locationTemplate = this.template(location.toJSON());
           $(this.el).append(locationTemplate);
       }, this);

       return this;
   }
});

window.LocationListItemView = Backbone.View.extend({
    tagName:"li",
    template:_.template($('#locationTemplate').html()),
    render:function (eventName) {
		console.log('Debug new LocationListItemView');
		console.log('LocationListItemView render...');
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }
});

window.LocationView = Backbone.View.extend({
    el: $('#mainArea'),
    template:_.template($('#location-details').html()),
    render:function (eventName) {
		console.log('Debug LocationView');
        $(this.el).html(this.template(this.model.toJSON()));
        return this;
    }

});
   
var locationList = new LocationCollection();
var locationListView = new LocationListView({ model: locationList });
locationList.fetch();
locationList.bind('sync', function () { locationListView.render();});

//Router - handles list selection to show detail view
var AppRouter = Backbone.Router.extend({
    routes:{
        "":"list",
        "location/:id":"locationDetails"
    },
   
    list:function () {
        this.locationList = new LocationCollection();
        this.locationListView = new LocationListView({ model: this.locationList });
        this.locationList.fetch();
        this.locationList.bind('sync', function () { locationListView.render();});
    },

    locationDetails:function (id) {
       //this.locationList = new LocationCollection();
        this.location = this.locationList.get(id);
        this.locationView = new LocationView({model:this.location});
        $('#content').html(this.locationView.render().el);
    }
});

var app = new AppRouter();
Backbone.history.start();

