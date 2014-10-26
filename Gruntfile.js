module.exports = function(grunt) {

  // config
  grunt.initConfig( {
    pkg: grunt.file.readJSON('package.json'),

    exec: {
      fastOptJS: {
        command: 'sbt fastOptJS'
      }
    },

    bower: {
      install: {
        options: {
          targetDir: 'bower_components/',
          // install: true,
          // cleanTargetDir: false,
          // cleanBowerDir: false,
          // copy: true,
          // cleanup: undefined,
          // layout: 'byType',
          verbose: true,
          bowerOptions: { }
        }
      }
    },

    copy: {
      build: {
        files: [
          {
            expand: true,
            cwd: 'target/scala-2.11',
            src: '**/*-fastopt.js*',
            dest: 'public/scalajs/',
            filter: 'isFile'
          },
          {
            expand: true,
            cwd: 'bower_components/knockout/dist/',
            src: '**/knockout.js',
            dest: 'public/knockout/',
            filter: 'isFile'
          },
          {
            expand: true,
            cwd: 'bower_components/jquery/dist/',
            src: '**/*.min.*',
            dest: 'public/jquery/',
            filter: 'isFile'
          },
          {
            expand: true,
            cwd: 'bower_components/bootstrap/dist/',
            src: '**/*.min.*',
            dest: 'public/bootstrap/',
            filter: 'isFile'
          },
          {
            expand: true,
            cwd: 'bower_components/bootstrap/dist/fonts',
            src: '**/*',
            dest: 'public/bootstrap/fonts',
            filter: 'isFile'
          },
          {
            expand: true,
            cwd: 'bower_components/masonry/dist/',
            src: '**/*.min.js',
            dest: 'public/masonry/',
            filter: 'isFile'
          }
        ]
      }
    }
  });

  // plugins
  grunt.loadNpmTasks('grunt-bower-task');
  grunt.loadNpmTasks('grunt-contrib-copy');
  grunt.loadNpmTasks('grunt-exec');
  grunt.loadNpmTasks('grunt-notify');

  // tasks
  grunt.registerTask('init', ['exec:fastOptJS', 'bower:install', 'copy:build']);
};

