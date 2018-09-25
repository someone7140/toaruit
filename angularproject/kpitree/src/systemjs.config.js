/**
 * System configuration for Angular samples
 * Adjust as necessary for your application needs.
 */
(function (global) {
  System.config({
    paths: {
      // paths serve as alias
      'npm:': 'node_modules/'
    },
    // map tells the System loader where to look for things
    map: {
      // our app is within the app folder
      'app': 'app',

      // angular bundles
      '@angular/core': 'npm:@angular/core/bundles/core.umd.js',
      '@angular/common': 'npm:@angular/common/bundles/common.umd.js',
      '@angular/common/http': 'npm:@angular/common/bundles/common-http.umd.min.js',
      '@angular/compiler': 'npm:@angular/compiler/bundles/compiler.umd.js',
      '@angular/platform-browser': 'npm:@angular/platform-browser/bundles/platform-browser.umd.js',
      '@angular/platform-browser-dynamic': 'npm:@angular/platform-browser-dynamic/bundles/platform-browser-dynamic.umd.js',
      '@angular/http': 'npm:@angular/http/bundles/http.umd.js',
      '@angular/router': 'npm:@angular/router/bundles/router.umd.js',
      '@angular/forms': 'npm:@angular/forms/bundles/forms.umd.js',
      '@angular/material': 'npm:@angular/material/bundles/material.umd.min.js',
      '@angular/cdk': 'npm:@angular/cdk/bundles/cdk.umd.min.js',
      '@angular/cdk/a11y': 'npm:@angular/cdk/bundles/cdk-a11y.umd.min.js',
      '@angular/cdk/accordion': 'npm:@angular/cdk/bundles/cdk-accordion.umd.min.js',
      '@angular/cdk/bidi': 'npm:@angular/cdk/bundles/cdk-bidi.umd.min.js',
      '@angular/cdk/coercion': 'npm:@angular/cdk/bundles/cdk-coercion.umd.min.js',
      '@angular/cdk/collections': 'npm:@angular/cdk/bundles/cdk-collections.umd.min.js',
      '@angular/cdk/keycodes': 'npm:@angular/cdk/bundles/cdk-keycodes.umd.min.js',
      '@angular/cdk/layout': 'npm:@angular/cdk/bundles/cdk-layout.umd.min.js',
      '@angular/cdk/observers': 'npm:@angular/cdk/bundles/cdk-observers.umd.min.js',
      '@angular/cdk/overlay': 'npm:@angular/cdk/bundles/cdk-overlay.umd.min.js',
      '@angular/cdk/platform': 'npm:@angular/cdk/bundles/cdk-platform.umd.min.js',
      '@angular/cdk/portal': 'npm:@angular/cdk/bundles/cdk-portal.umd.min.js',
      '@angular/cdk/scrolling': 'npm:@angular/cdk/bundles/cdk-scrolling.umd.min.js',
      '@angular/cdk/stepper': 'npm:@angular/cdk/bundles/cdk-stepper.umd.min.js',
      '@angular/cdk/table': 'npm:@angular/cdk/bundles/cdk-table.umd.min.js',
      'tslib': 'npm:tslib/tslib.js',
      // other libraries
      '@swimlane/ngx-charts':'npm:@swimlane/ngx-charts/release/index.js',
      '@swimlane/ngx-graph':'npm:@swimlane/ngx-graph/release/index.js',
      '@angular/animations': 'npm:@angular/animations/bundles/animations.umd.min.js',
      '@angular/animations/browser': 'npm:@angular/animations/bundles/animations-browser.umd.js',
      '@angular/platform-browser/animations': 'npm:@angular/platform-browser/bundles/platform-browser-animations.umd.js',
      'd3-array': 'npm:d3-array/build/d3-array.min.js',
      'd3-brush': 'npm:d3-brush/build/d3-brush.min.js',
      'd3-color': 'npm:d3-color/build/d3-color.min.js',
      'd3-collection': 'npm:d3-collection/build/d3-collection.min.js',
      'd3-dispatch': 'npm:d3-dispatch/build/d3-dispatch.min.js',
      'd3-drag': 'npm:d3-drag/build/d3-drag.min.js',
      'd3-ease': 'npm:d3-ease/build/d3-ease.min.js',
      'd3-force': 'npm:d3-force/build/d3-force.min.js',
      'd3-format': 'npm:d3-format/build/d3-format.min.js',
      'd3-hierarchy': 'npm:d3-hierarchy/build/d3-hierarchy.min.js',
      'd3-interpolate': 'npm:d3-interpolate/build/d3-interpolate.min.js',
      'd3-path': 'npm:d3-path/build/d3-path.min.js',
      'd3-quadtree': 'npm:d3-quadtree/build/d3-quadtree.min.js',
      'd3-scale': 'npm:d3-scale/build/d3-scale.min.js',
      'd3-selection': 'npm:d3-selection/dist/d3-selection.min.js',
      'd3-shape': 'npm:d3-shape/build/d3-shape.min.js',
      'd3-time-format': 'npm:d3-time-format/build/d3-time-format.min.js',
      'd3-time': 'npm:d3-time/build/d3-time.min.js',
      'd3-timer': 'npm:d3-timer/build/d3-timer.min.js',
      'd3-transition': 'npm:d3-transition/build/d3-transition.min.js',
      'rxjs':                      'npm:rxjs',
      'angular-in-memory-web-api': 'npm:angular-in-memory-web-api/bundles/in-memory-web-api.umd.js'
    },
    // packages tells the System loader how to load when no filename and/or no extension
    packages: {
      app: {
        defaultExtension: 'js',
        meta: {
          './*.js': {
            loader: 'systemjs-angular-loader.js'
          }
        }
      },
      rxjs: {
        defaultExtension: 'js'
      }
    }
  });
})(this);
