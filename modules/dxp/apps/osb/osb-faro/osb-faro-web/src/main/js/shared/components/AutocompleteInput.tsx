import ClayAutocomplete from '@clayui/autocomplete';
import getCN from 'classnames';
import React, {useEffect, useState} from 'react';
import {DocumentNode} from 'apollo-boost';
import {NetworkStatus} from '@clayui/data-provider';
import {useDebounce} from 'shared/hooks/useDebounce';
import {useQuery} from '@apollo/react-hooks';
import {useRequest} from 'shared/hooks/useRequest';

type TMappedData = {
	data: string[];
	total: number;
};

type GraphqlQuery = {
	mapResultsToProps: (data: any) => TMappedData;
	variables: object;
	query: DocumentNode;
};

interface IAutocompleteProps {
	className?: string;
	dataSourceFn?: (query?: string) => Promise<string[]>;
	disabled?: boolean;
	graphqlQuery?: GraphqlQuery;
	placeholder?: string;
	testId?: string;
	value: string;
	onBlur?: React.FocusEventHandler<HTMLInputElement>;
	onChange?: (value: string) => void;
}

const DEBOUNCE_DELAY = 250;

const AutocompleteInput: React.FC<IAutocompleteProps> = ({
	className,
	dataSourceFn,
	disabled = false,
	graphqlQuery,
	onBlur,
	onChange,
	placeholder,
	value
}) => {
	const [networkStatus, setNetworkStatus] = useState(NetworkStatus.Unused);

	let response;

	if (graphqlQuery) {
		const {
			mapResultsToProps = value => value,
			query,
			variables
		} = graphqlQuery;
		const debouncedInputValue = useDebounce(value, DEBOUNCE_DELAY);

		response = useQuery(query, {
			fetchPolicy: 'network-only',
			variables: {
				...variables,
				keywords: debouncedInputValue
			}
		});

		response = {
			...response,
			...mapResultsToProps(response.data)
		};
	} else {
		response = useRequest({
			dataSourceFn: ({value}) => dataSourceFn(value),
			debounceDelay: DEBOUNCE_DELAY,
			initialState: {
				data: [],
				error: false,
				loading: false
			},
			variables: {value}
		});
	}

	const {data: items = [], loading} = response;

	useEffect(() => {
		setNetworkStatus(
			loading ? NetworkStatus.Loading : NetworkStatus.Unused
		);
	}, [loading]);

	return (
		<ClayAutocomplete
			allowsCustomValue
			aria-labelledby='clay-autocomplete-label-1'
			className={getCN('select-input-root', className)}
			data-testid='attribute-value-string-input'
			disabled={disabled}
			id='clay-autocomplete-1'
			items={items as string[]}
			loadingState={networkStatus}
			menuTrigger='focus'
			messages={{
				loading: Liferay.Language.get('loading'),
				notFound: Liferay.Language.get('no-results-were-found')
			}}
			onBlur={onBlur}
			onChange={onChange}
			placeholder={placeholder}
			value={value}
		>
			{item => (
				<ClayAutocomplete.Item key={item}>{item}</ClayAutocomplete.Item>
			)}
		</ClayAutocomplete>
	);
};

export default AutocompleteInput;
