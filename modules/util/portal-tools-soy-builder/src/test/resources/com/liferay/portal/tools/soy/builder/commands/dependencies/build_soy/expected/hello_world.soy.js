// This file was automatically generated from hello_world.soy.
// Please don't edit this file by hand.

/**
 * @fileoverview Templates in namespace samples.
 * @public
 */

goog.provide('samples');

goog.requireType('soy');
goog.require('soydata.VERY_UNSAFE');


/**
 * @param {Object<string, *>=} opt_data
 * @param {soy.IjData|Object<string, *>=} opt_ijData
 * @param {soy.IjData|Object<string, *>=} opt_ijData_deprecated
 * @return {!goog.soy.data.SanitizedHtml}
 * @suppress {checkTypes}
 */
samples.helloWorld = function(opt_data, opt_ijData, opt_ijData_deprecated) {
  opt_ijData = /** @type {!soy.IjData} */ (opt_ijData_deprecated || opt_ijData);
  return soydata.VERY_UNSAFE.ordainSanitizedHtml('Hello world!');
};
if (goog.DEBUG) {
  samples.helloWorld.soyTemplateName = 'samples.helloWorld';
}
