// Author: Thomas Davis <thomasalwyndavis@gmail.com>
// Filename: main.js

// Require.js allows us to configure shortcut alias
// Their usage will become more apparent futher along in the tutorial.
require.config({
  paths: {
    jquery: '/scripts/libs/jquery/1.11.2/jquery-min',
    underscore: '/scripts/libs/underscore/1.8.3/underscore-min',
    backbone: '/scripts/libs/backbone/1.1.2/backbone-min',
    templates: '/templates'
  }

});

require([
  // Load our app module and pass it to our definition function
  'app',

], function(App){
  // The "app" dependency is passed in as "App"
  // Again, the other dependencies passed in are not "AMD" therefore don't pass a parameter to this function
  App.initialize();
});
