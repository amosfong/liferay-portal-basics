import React from 'react';
import {isEqual} from 'lodash';

function useUpdateValueOnChange(value) {
	const ref = React.useRef();

	if (!isEqual(value, ref.current)) {
		ref.current = value;
	}

	return ref.current;
}

export function useDeepEqualEffect(callback, args) {
	React.useEffect(callback, useUpdateValueOnChange(args));
}