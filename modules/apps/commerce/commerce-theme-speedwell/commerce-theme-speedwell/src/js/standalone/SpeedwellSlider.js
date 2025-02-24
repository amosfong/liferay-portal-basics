/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

const CURRENT = 'current';
const NEXT = 'next';
const WILL_BE_NEXT = 'will-be-next';

const STATES_MAP = {
	[CURRENT]: {
		backwards: NEXT,
		forwards: WILL_BE_NEXT,
	},
	[NEXT]: {
		backwards: WILL_BE_NEXT,
		forwards: CURRENT,
	},
	[WILL_BE_NEXT]: {
		backwards: CURRENT,
		forwards: NEXT,
	},
};

const BACKWARDS = 'backwards';
const FORWARDS = 'forwards';

function validateInterval(interval) {
	const MIN = 4000;

	if (interval > 0 && interval <= MIN) {
		return MIN;
	}
	else if (interval > MIN) {
		return interval;
	}

	return null;
}

const SpeedwellSlider = function (
	sliderContainer,
	setupDOMSlideFn,
	renderSlideContentFn,
	interval
) {
	this.sliderWrapper = sliderContainer;
	this.setupDOMSlide = setupDOMSlideFn;
	this.renderSlideContent = renderSlideContentFn;
	this.interval = validateInterval(interval);

	if (this.sliderWrapper) {
		this.init();
	}
	else {
		throw new Error('Container not found.');
	}
};

SpeedwellSlider.prototype = {
	applyAnimation(direction, nextSlideContent) {
		return new Promise((restoreInteraction) => {
			const currentSlide = this.sliderWrapper.querySelector(
				'[data-state="current"]'
			);

			currentSlide.addEventListener(
				'webkitTransitionEnd',
				this.handleSlideChange.bind(
					this,
					direction,
					restoreInteraction,
					nextSlideContent
				),
				{once: true}
			);

			this.slides.forEach((slide) => {
				slide.classList.add(`is-sliding-${direction}`);
			});
		});
	},
	attachListeners() {
		this.controls = {
			container: this.sliderWrapper.querySelector('div[class*=controls]'),
			nextBtn: this.sliderWrapper.querySelector(
				'button[class*=control--next]'
			),
			prevBtn: this.sliderWrapper.querySelector(
				'button[class*=control--prev]'
			),
		};

		this.controls.prevBtn.addEventListener(
			'click',
			this.throttleInteraction.bind(this),
			true
		);
		this.controls.nextBtn.addEventListener(
			'click',
			this.throttleInteraction.bind(this),
			true
		);

		this.checkControls();
	},

	checkControls() {
		if (this.interval) {
			this.controls.container.classList.add('self-sliding');
			this.interval = setInterval(() => {
				this.throttleInteraction(null);
			}, this.interval);
		}
	},

	constructor: SpeedwellSlider,
	controls: {
		nextBtn: null,
		prevBtn: null,
	},
	dataset: [],
	datasetSize: null,
	defaultSetup() {
		const dataset = this.dataset;
		const that = this;

		Object.keys(STATES_MAP).forEach((state, index) => {
			that.setupDOMSlide(state, index, dataset);
			that.stateCycleMap[state] = dataset[index];
		});
	},
	didPrepare(nextSlideContent) {
		return Promise.resolve(nextSlideContent);
	},
	getNextSlideContent(direction) {
		const nextSlideIndex =
			direction === FORWARDS
				? this.stateCycleMap[WILL_BE_NEXT].index + 1
				: this.stateCycleMap[CURRENT].index - 1;

		if (this.dataset[nextSlideIndex]) {
			return this.dataset[nextSlideIndex];
		}

		return direction === FORWARDS
			? this.dataset[0]
			: this.dataset[this.datasetSize - 1];
	},
	handleSlideChange(direction, restoreInteraction, nextSlideContent) {
		this.slides.forEach((slide) => this.setNextState(direction, slide));

		const $prepare = nextSlideContent
			? this.didPrepare(nextSlideContent)
			: this.prepareNow(direction);

		$prepare.then((slideContent) => {
			this.updateStateCycle(direction, slideContent);
			if (restoreInteraction) {
				restoreInteraction({isEnabled: true});
			}
		});
	},
	init() {
		this.setupData()
			.then(this.setupSliders.bind(this))
			.then(this.attachListeners.bind(this))
			.catch((error) => {
				const errorMessage =
					`Request code: ${error.statusCode.toString()}` ||
					'API error';

				Liferay.Util.openToast({
					message: errorMessage,
					title: '',
					type: 'danger',
				});
			});
	},

	interval: null,

	oneSlideSetup() {
		this.removeControls();
		this.setupDOMSlide(CURRENT, 0);

		this.sliderWrapper
			.querySelector('[data-state=current]')
			.classList.add('is-single-slide');
	},
	prepareLater() {
		return Promise.resolve(null);
	},

	prepareNextSlide(direction) {
		return new Promise((resolve) => {
			const nextSlideContent = this.getNextSlideContent(direction);

			this.renderSlideContent(this.sliderWrapper, nextSlideContent);

			resolve(nextSlideContent);
		});
	},

	prepareNow(direction) {
		return this.prepareNextSlide(direction);
	},
	removeControls() {
		const controlsElement = this.sliderWrapper.querySelector(
			'div[class*=controls]'
		);

		controlsElement.remove();
	},
	renderSlideContent() {},
	setNextState(direction, slide) {
		slide.classList.remove(`is-sliding-${direction}`);
		slide.dataset.state = STATES_MAP[slide.dataset.state][direction];
	},
	setupDOMSlide() {},

	setupData() {
		return new Promise((resolve, reject) => {
			try {
				const ldJson =
					this.sliderWrapper.querySelector(
						'.slider-dataset'
					).innerText;
				this.dataset = this.validateDataset(JSON.parse(ldJson));

				this.dataset.forEach((object, index) => {
					object.index = index;
				});

				this.datasetSize = this.dataset.length;
				resolve();
			}
			catch (error) {
				reject(new Error(error));
			}
		});
	},

	setupSliders() {
		return new Promise((resolve, reject) => {
			if (this.datasetSize === 0) {
				reject(new Error('No dataset size.'));
			}
			else if (this.datasetSize === 1) {
				this.oneSlideSetup();
			}
			else if (this.datasetSize === 2) {
				this.twoSlidesSetup();
			}
			else {
				this.defaultSetup();
			}

			this.slides = Array.from(
				this.sliderWrapper.querySelectorAll('[data-state]')
			);

			resolve();
		});
	},
	slides: [],
	stateCycleMap: {},

	throttleInteraction(event) {
		const direction =
			event instanceof Event &&
			event.currentTarget.className.indexOf('prev') > -1
				? BACKWARDS
				: FORWARDS;
		const prepare =
			direction === BACKWARDS
				? this.prepareNow.bind(this)
				: this.prepareLater;

		this.toggleControls({isEnabled: false});

		prepare(direction)
			.then((nextSlideContent) =>
				this.applyAnimation(direction, nextSlideContent)
			)
			.then((restore) => this.toggleControls(restore));
	},

	toggleControls({isEnabled}) {
		if (isEnabled) {
			this.controls.prevBtn.removeAttribute('disabled');
			this.controls.nextBtn.removeAttribute('disabled');
		}
		else {
			this.controls.prevBtn.setAttribute('disabled', isEnabled);
			this.controls.nextBtn.setAttribute('disabled', isEnabled);
		}
	},

	twoSlidesSetup() {
		this.dataset.push(this.dataset[0]);
		this.defaultSetup();
	},

	updateStateCycle(direction, nextSlideContent) {
		if (direction === FORWARDS) {
			this.stateCycleMap[CURRENT] = this.stateCycleMap[NEXT];
			this.stateCycleMap[NEXT] = this.stateCycleMap[WILL_BE_NEXT];
			this.stateCycleMap[WILL_BE_NEXT] = nextSlideContent;
		}
		else {
			this.stateCycleMap[WILL_BE_NEXT] = this.stateCycleMap[NEXT];
			this.stateCycleMap[NEXT] = this.stateCycleMap[CURRENT];
			this.stateCycleMap[CURRENT] = nextSlideContent;
		}
	},

	validateDataset(data) {
		return data;
	},
};

Liferay.component(
	'SpeedwellSlider',
	(function () {
		return {
			initialize(setupDOMSlideFn, renderSlideContentFn, interval) {
				const sliderContainer =
					window.document.querySelector('[data-will-load]');

				sliderContainer.removeAttribute('data-will-load');

				return new SpeedwellSlider(
					sliderContainer,
					setupDOMSlideFn,
					renderSlideContentFn,
					interval
				);
			},
		};
	})()
);
