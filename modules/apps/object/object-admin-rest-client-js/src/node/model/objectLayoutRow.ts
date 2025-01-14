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
import { ObjectLayoutColumn } from './objectLayoutColumn';

export class ObjectLayoutRow {
    'id'?: number;
    'objectLayoutColumns'?: Array<ObjectLayoutColumn>;
    'priority'?: number;

    static discriminator: string | undefined = undefined;

    static attributeTypeMap: Array<{name: string, baseName: string, type: string}> = [
        {
            "name": "id",
            "baseName": "id",
            "type": "number"
        },
        {
            "name": "objectLayoutColumns",
            "baseName": "objectLayoutColumns",
            "type": "Array<ObjectLayoutColumn>"
        },
        {
            "name": "priority",
            "baseName": "priority",
            "type": "number"
        }    ];

    static getAttributeTypeMap() {
        return ObjectLayoutRow.attributeTypeMap;
    }
}

