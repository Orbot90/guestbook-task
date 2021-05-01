/**
 * @license Copyright (c) 2003-2021, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or https://ckeditor.com/legal/ckeditor-oss-license
 */

// The editor creator to use.
import InlineEditorBase from '@ckeditor/ckeditor5-editor-inline/src/inlineeditor';

import Essentials from '@ckeditor/ckeditor5-essentials/src/essentials';
import Bold from '@ckeditor/ckeditor5-basic-styles/src/bold';
import Italic from '@ckeditor/ckeditor5-basic-styles/src/italic';
import Image from '@ckeditor/ckeditor5-image/src/image';
import ImageStyle from '@ckeditor/ckeditor5-image/src/imagestyle';
import ImageInsert from '@ckeditor/ckeditor5-image/src/imageinsert';
import ImageToolbar from '@ckeditor/ckeditor5-image/src/imagetoolbar';
import AutoImage from '@ckeditor/ckeditor5-image/src/autoimage';
import ImageUpload from '@ckeditor/ckeditor5-image/src/imageupload';
import Link from '@ckeditor/ckeditor5-link/src/link';
import TextTransformation from '@ckeditor/ckeditor5-typing/src/texttransformation';
import FontColor from '@ckeditor/ckeditor5-font/src/fontcolor';
import FontSize from '@ckeditor/ckeditor5-font/src/fontsize';
import RemoveFormat from '@ckeditor/ckeditor5-remove-format/src/removeformat';
import Base64UploadAdapter from '@ckeditor/ckeditor5-upload/src/adapters/base64uploadadapter';
import Heading from '@ckeditor/ckeditor5-heading/src/heading'
import SimpleUploadAdapter from '@ckeditor/ckeditor5-upload/src/adapters/simpleuploadadapter';

export default class InlineEditor extends InlineEditorBase {}

// Plugins to include in the build.
InlineEditor.builtinPlugins = [
	Essentials,
	Bold,
	Italic,
	Image,
	ImageStyle,
	ImageToolbar,
	ImageUpload,
	Link,
	TextTransformation,
	ImageInsert,
	AutoImage,
	FontColor,
	FontSize,
	Base64UploadAdapter,
	RemoveFormat,
	Heading,
	SimpleUploadAdapter
];

// Editor configuration.
InlineEditor.defaultConfig = {
	fontSize: {
		options: [
			'tiny',
			'default',
			'big'
		]
	},
	fontFamily: {
		options: [
			'default',
			'Ubuntu, Arial, sans-serif',
			'Ubuntu Mono, Courier New, Courier, monospace'
		]
	},
	fontColor: {
		colors: [
			{
				color: 'hsl(0, 0%, 0%)',
				label: 'Black'
			},
			{
				color: 'hsl(0, 0%, 30%)',
				label: 'Dim grey'
			},
			{
				color: 'hsl(0, 0%, 60%)',
				label: 'Grey'
			},
			{
				color: 'hsl(0, 0%, 90%)',
				label: 'Light grey'
			},
			{
				color: 'hsl(0, 0%, 100%)',
				label: 'White',
				hasBorder: true
			},

			// ...
		]
	},
	toolbar: {
		items: [
			'bold',
			'italic',
			'|',
			'link',
			'uploadImage',
			'|',
			'fontFamily',
			'fontColor',
			'fontSize',
			'|',
			'removeFormat'	

		]
	},
	// This value must be kept in sync with the language defined in webpack.config.js.
	language: 'en'
};
