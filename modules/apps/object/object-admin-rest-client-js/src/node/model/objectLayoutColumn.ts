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

export class ObjectLayoutColumn {
    'id'?: number;
    'objectFieldName'?: string;
    'priority'?: number;
    'size'?: number;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "id",
            "baseName": "id",
            "type": "number"
        },
        {
            "name": "objectFieldName",
            "baseName": "objectFieldName",
            "type": "string"
        },
        {
            "name": "priority",
            "baseName": "priority",
            "type": "number"
        },
        {
            "name": "size",
            "baseName": "size",
            "type": "number"
        }    ];

    static getAttributeTypeMap() {
        return ObjectLayoutColumn.attributeTypeMap;
    }
}
