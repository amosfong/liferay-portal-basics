/**
 * Object
 * A Java client JAR is available for use with the group ID \'com.liferay\', artifact ID \'com.liferay.object.admin.rest.client\', and version \'1.0.71\'.
 *
 * The version of the OpenAPI document: v1.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { RequestFile } from './models';
import { Facet } from './facet';
import { ObjectValidationRule } from './objectValidationRule';

export class PageObjectValidationRule {
    'actions'?: { [key: string]: { [key: string]: string; }; };
    'facets'?: Array<Facet>;
    'items'?: Array<ObjectValidationRule>;
    'lastPage'?: number;
    'page'?: number;
    'pageSize'?: number;
    'totalCount'?: number;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "actions",
            "baseName": "actions",
            "type": "{ [key: string]: { [key: string]: string; }; }"
        },
        {
            "name": "facets",
            "baseName": "facets",
            "type": "Array<Facet>"
        },
        {
            "name": "items",
            "baseName": "items",
            "type": "Array<ObjectValidationRule>"
        },
        {
            "name": "lastPage",
            "baseName": "lastPage",
            "type": "number"
        },
        {
            "name": "page",
            "baseName": "page",
            "type": "number"
        },
        {
            "name": "pageSize",
            "baseName": "pageSize",
            "type": "number"
        },
        {
            "name": "totalCount",
            "baseName": "totalCount",
            "type": "number"
        }    ];

    static getAttributeTypeMap() {
        return PageObjectValidationRule.attributeTypeMap;
    }
}

