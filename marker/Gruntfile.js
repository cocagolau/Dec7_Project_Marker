module.exports = function(grunt) {

	// 프로젝트 환경설정.
	grunt.initConfig({
		pkg: grunt.file.readJSON('package.json'),

		dirs: {
			src	: 'websrc',
			scripts	: 'scripts',
			scriptsVendor	: 'scripts/vendor',
			stylesheets	: 'stylesheets',
			stylesheetsVendor	: 'stylesheets/vendor',
			img	: 'img',
			static : 'static',
			templates : 'templates',
			pages : 'pages',
			less	: 'css',
			dest	: 'webapp/WEB-INF'
		},
		
		config: {
			bowerFile	: '_bower',
			encoding	: 'UTF-8',
			projectName	: 'marker'
		},

		// tasks
		clean : {
			all : [
				'**/_bower*.*',
				'<%= dirs.dest %>'
			]
		},

//		bower_concat : {
//			all : {
//				dest : '<%= dirs.src %>/<%= dirs.scriptsVendor %>/<%= config.bowerFile %>.js',
//				mainFiles: {
//					'jQuery': ['src/ajax.js']
//				},
//				cssDest : '<%= dirs.src %>/<%= dirs.stylesheetsVendor %>/<%= config.bowerFile %>.css'
//			}
//		},
		
//		concat: {
//			jquery: {
//				files: {
//					'scripts/vendor/angular/angular.js': ['bower_components/angular/angular.js']
//				}
//			},
//		}
		
		bowercopy: {
			options: {
				srcPrefix: 'bower_components'
			},
			scripts: {
				options: {
					destPrefix: '<%= dirs.dest %>/<%= dirs.scriptsVendor %>'
				},
				files: {
					// dest: src
					'requirejs/require.js': 'requirejs/require.js',
					'jquery/jquery.js': 'jquery/dist/jquery.js',
					'backbone/backbone.js': 'backbone/backbone.js',
					'underscore/underscore.js': 'underscore/underscore.js'
				}
			}
		},
		
		

		copy : {
			js : {
				files : [ {
					expand : true,
					cwd : '<%= dirs.src %>/<%= dirs.scripts %>/',
					src : [ '**/*.js' ],
					dest : '<%= dirs.dest %>/<%= dirs.scripts %>/'
				} ]
			},
			css : {
				files : [ {
					expand : true,
					cwd : '<%= dirs.src %>/<%= dirs.stylesheets %>/',
					src : [ '**/*.css' ],
					dest : '<%= dirs.dest %>/<%= dirs.stylesheets %>/'
				} ]
			},
			static : {
				files : [ {
						expand : true,
						cwd : '<%= dirs.src %>/<%= dirs.static %>/',
						src : [ '**/*' ],
						dest : '<%= dirs.dest %>/<%= dirs.static %>/'
					} ]
				},
			pages : {
				files : [ {
						expand : true,
						cwd : '<%= dirs.src %>/<%= dirs.pages %>/',
						src : [ '**/*' ],
						dest : '<%= dirs.dest %>/<%= dirs.pages %>/'
					} ]
				},
			templates : {
				files : [ {
						expand : true,
						cwd : '<%= dirs.src %>/<%= dirs.templates %>/',
						src : [ '**/*' ],
						dest : '<%= dirs.dest %>/<%= dirs.templates %>/'
					} ]
				},
		},
		

		jshint : {
			options : {
				force : true
			},
			all : [
				'Gruntfile.js',
				'<%= dirs.src %>/<%= dirs.scripts %>/**/*.js',
				'!<%= dirs.src %>/<%= dirs.scriptsVendor %>/**/*.js'
			]
		},

		less : {
			all : {
				options : {
					compress : true,
					sourceMap : true,
					sourceMapFileInline : true,
					outputSourceFiles : true,
					plugins : [
							new (require('less-plugin-autoprefix'))({
								browsers : [ "last 2 versions" ]
							}),
							new (require('less-plugin-clean-css'))() ]
				},
				files : [ {
					expand : true,
					cwd : '<%= dirs.src %>/<%= dirs.less %>/',
					src : [ '**/*.less' ],
					dest : '<%= dirs.dest %>/<%= dirs.stylesheets %>/',
					ext : '.css'
				} ],
			}
		},

		watch : {
			options : {
				spawn : false
			},

//			html : {
//				files : [ '<%= dirs.src %>/**/*.html' ],
//				tasks : [ 'htmlToJsp' ],
//				options : {
//					livereload : true
//				}
//			},

			js : {
				files : [ '<%= jshint.all %>' ],
				tasks : [ 'jshint', 'copy:js' ],
				options : {
					livereload : true
				}
			},

			css : {
				files : [ '<%= copy.css.files[0].cwd %>/<%= copy.css.files[0].src %>' ],
				tasks : [ 'copy:css' ],
				options : {
					livereload : true
				}
			},

			static : {
				files : [ '<%= copy.static.files[0].cwd %>/<%= copy.static.files[0].src %>' ],
				tasks : [ 'copy:static' ],
				options : {
					livereload : true
				}
			},

			less : {
				files : [ '<%= less.all.files[0].cwd %>/<%= less.all.files[0].src %>' ],
				tasks : [ 'less' ],
				options : {
					livereload : true
				}
			}
		},


		uglify: {
 			options: {
				banner: '/*! <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> */\n'
			},
			build: {
				src: 'src/<%= pkg.name %>.js',
				dest: 'build/<%= pkg.name %>.min.js'
			},
//			bower : {
//				options : {
//					mangle : true,
//					compress : true
//				},
//				files : {
//					'js/bower.min.js' : 'js/bower.js'
//				}
//			}
		}
	});
	
	
	// "uglify" task를 지원하는 플러그인 로드.
	grunt.loadNpmTasks('grunt-contrib-uglify');
	grunt.loadNpmTasks('grunt-bower-concat');
	grunt.loadNpmTasks('grunt-bowercopy');
	grunt.loadNpmTasks('grunt-contrib-clean');
	grunt.loadNpmTasks('grunt-contrib-copy');
	grunt.loadNpmTasks('grunt-contrib-jshint');
	grunt.loadNpmTasks('grunt-contrib-watch');
	grunt.loadNpmTasks('grunt-contrib-less');

	// Default task(s).
	grunt.registerTask('default', [
				'clean',
				'jshint',
				'bowercopy',
//				'bower_concat',
				'copy',
				'less'
			]);
};
