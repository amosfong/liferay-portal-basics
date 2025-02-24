import * as UTILS from '../utils';
import {DataTypes} from 'event-analysis/utils/types';
import {
	FunctionalOperators,
	NotOperators,
	RelationalOperators
} from '../../../../utils/constants';

describe('Utils', () => {
	describe('createOption', () => {
		it.each`
			option                          | dataType              | label
			${'true'}                       | ${DataTypes.Boolean}  | ${'True'}
			${'false'}                      | ${DataTypes.Boolean}  | ${'False'}
			${RelationalOperators.EQ}       | ${DataTypes.Date}     | ${'is'}
			${RelationalOperators.GT}       | ${DataTypes.Date}     | ${'is after'}
			${RelationalOperators.LT}       | ${DataTypes.Date}     | ${'is before'}
			${RelationalOperators.GT}       | ${DataTypes.Duration} | ${'is after'}
			${RelationalOperators.LT}       | ${DataTypes.Duration} | ${'is before'}
			${RelationalOperators.GT}       | ${DataTypes.Number}   | ${'greater than'}
			${RelationalOperators.LT}       | ${DataTypes.Number}   | ${'less than'}
			${FunctionalOperators.Contains} | ${DataTypes.String}   | ${'contains'}
			${NotOperators.NotContains}     | ${DataTypes.String}   | ${'does not contain'}
			${RelationalOperators.EQ}       | ${DataTypes.String}   | ${'is'}
			${RelationalOperators.NE}       | ${DataTypes.String}   | ${'is not'}
		`(
			'should return label $label & value $option when option $option & dataType $dataType',
			({dataType, label, option}) => {
				expect(UTILS.createOption(option, dataType)).toEqual({
					label,
					value: option
				});
			}
		);
	});

	describe('getOperatorOptions', () => {
		it.each`
			dataType              | count
			${DataTypes.Date}     | ${3}
			${DataTypes.Duration} | ${3}
			${DataTypes.Number}   | ${4}
			${DataTypes.String}   | ${4}
		`(
			'should return $count options when dataType is $dataType',
			({count, dataType}) => {
				expect(UTILS.getOperatorOptions(dataType).length).toBe(count);
			}
		);
	});

	describe('getDefaultAttributeOperator', () => {
		it.each`
			dataType              | value
			${DataTypes.Boolean}  | ${RelationalOperators.EQ}
			${DataTypes.Date}     | ${RelationalOperators.EQ}
			${DataTypes.Duration} | ${RelationalOperators.GT}
			${DataTypes.Number}   | ${RelationalOperators.GT}
			${DataTypes.String}   | ${FunctionalOperators.Contains}
		`(
			'should return $value when dataType is $dataType',
			({dataType, value}) => {
				expect(UTILS.getDefaultAttributeOperator(dataType)).toBe(value);
			}
		);
	});

	describe('getDefaultAttributeValue', () => {
		it.each`
			dataType              | operatorName                    | value
			${DataTypes.Boolean}  | ${RelationalOperators.EQ}       | ${'true'}
			${DataTypes.Date}     | ${RelationalOperators.EQ}       | ${''}
			${DataTypes.Date}     | ${FunctionalOperators.Between}  | ${{end: '', start: ''}}
			${DataTypes.Duration} | ${RelationalOperators.GT}       | ${''}
			${DataTypes.Number}   | ${RelationalOperators.GT}       | ${''}
			${DataTypes.Number}   | ${FunctionalOperators.Between}  | ${{end: '', start: ''}}
			${DataTypes.String}   | ${FunctionalOperators.Contains} | ${''}
		`(
			'should return $value when dataType is $dataType',
			({dataType, operatorName, value}) => {
				expect(
					UTILS.getDefaultAttributeValue(dataType, operatorName)
				).toEqual(value);
			}
		);
	});

	describe('validateAttributeValue', () => {
		it.each`
			dataType              | value           | valid
			${DataTypes.Boolean}  | ${'true'}       | ${true}
			${DataTypes.Boolean}  | ${'false'}      | ${true}
			${DataTypes.Boolean}  | ${''}           | ${false}
			${DataTypes.Date}     | ${'2021-12-12'} | ${true}
			${DataTypes.Duration} | ${123123}       | ${true}
			${DataTypes.Duration} | ${'123123'}     | ${false}
			${DataTypes.Number}   | ${123}          | ${true}
			${DataTypes.Number}   | ${''}           | ${false}
			${DataTypes.String}   | ${'stuff'}      | ${true}
			${DataTypes.String}   | ${''}           | ${false}
		`(
			'should return $valid when dataType is $dataType and value is $value',
			({dataType, valid, value}) => {
				expect(UTILS.validateAttributeValue(value, dataType)).toEqual(
					valid
				);
			}
		);
	});
});
