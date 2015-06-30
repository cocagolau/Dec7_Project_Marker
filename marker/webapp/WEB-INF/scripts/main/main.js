// Author: Thomas Davis <thomasalwyndavis@gmail.com>
// Filename: main.js

// Require.js allows us to configure shortcut alias
// Their usage will become more apparent futher along in the tutorial.
require.config({
	paths : {
		jquery : '/scripts/vendor/jquery/jquery',
		underscore : '/scripts/vendor/underscore/underscore',
		backbone : '/scripts/vendor/backbone/backbone',
		templates : '/templates'
	}

});

require([
// Load our app module and pass it to our definition function
'app',

], function(App) {
	// The "app" dependency is passed in as "App"
	// Again, the other dependencies passed in are not "AMD" therefore don't pass a parameter to this function
	App.initialize();
});
