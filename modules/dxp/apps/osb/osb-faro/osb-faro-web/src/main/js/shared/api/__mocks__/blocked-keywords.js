import {mockBlockedKeyword, mockSearch} from 'test/data';

export const search = jest.fn(() =>
	Promise.resolve(mockSearch(mockBlockedKeyword))
);
