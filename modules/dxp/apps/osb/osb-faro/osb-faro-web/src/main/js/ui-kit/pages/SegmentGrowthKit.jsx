import Growth from 'segment/components/Growth';
import React from 'react';
import {mockSegment} from 'test/data';
import {ONE_DAY} from 'shared/util/constants';
import {Segment} from 'shared/util/records';
import {times} from 'lodash';

function getRandomInt(max) {
	return Math.floor(Math.random() * Math.floor(max * 100));
}

const points = times(90, i => ({
	modifiedDate: Date.now() - ONE_DAY * i,
	value: getRandomInt(i)
})).reverse();

export default class SegmentGrowthKit extends React.Component {
	render() {
		return (
			<div
				className={
					this.props.className ? ` ${this.props.className}` : ''
				}
			>
				<Growth
					data={points}
					groupId='35205'
					id='AWOKZWGtYGgetnXCCc8L'
					segment={new Segment(mockSegment())}
				/>
			</div>
		);
	}
}