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
import { ObjectLayoutRow } from './objectLayoutRow';

export class ObjectLayoutBox {
    'collapsable'?: boolean;
    'id'?: number;
    'name'?: { [key: string]: string; };
    'objectLayoutRows'?: Array<ObjectLayoutRow>;
    'priority'?: number;
    'type'?: ObjectLayoutBox.TypeEnum;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "collapsable",
            "baseName": "collapsable",
            "type": "boolean"
        },
        {
            "name": "id",
            "baseName": "id",
            "type": "number"
        },
        {
            "name": "name",
            "baseName": "name",
            "type": "{ [key: string]: string; }"
        },
        {
            "name": "objectLayoutRows",
            "baseName": "objectLayoutRows",
            "type": "Array<ObjectLayoutRow>"
        },
        {
            "name": "priority",
            "baseName": "priority",
            "type": "number"
        },
        {
            "name": "type",
            "baseName": "type",
            "type": "ObjectLayoutBox.TypeEnum"
        }    ];

    static getAttributeTypeMap() {
        return ObjectLayoutBox.attributeTypeMap;
    }
}

export namespace ObjectLayoutBox {
    export enum TypeEnum {
        Categorization = <any> 'categorization',
        Regular = <any> 'regular'
    }
}