import './PostEditor.css'
import React from 'react';
import Editor from '@ckeditor/ckeditor5-build-inline/build/ckeditor';
import {CKEditor} from '@ckeditor/ckeditor5-react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'
import {getApplicationProperty} from '../properties/applicationProperties'

class PostEditor extends React.Component {

    constructor(props) {
        super(props);
        this.imageUploadUrl = getApplicationProperty('imageUploadUrl')
        this.submit = this.submit.bind(this)

        this.state = {
            submitDisabled: true
        }
    }

    submit() {
        const data = this.editor.getData()
        console.log(data)
    }

    renderEditor() {
        return <CKEditor
                    editor={ Editor }
                    config={ {
                            placeholder: "Type your text here",
                            simpleUpload: {
                                uploadUrl: getApplicationProperty('imageUploadUrl')
                            }
                        }
                    }
                    onReady={ (editor) => {
                        this.editor = editor
                    }
                    }
                    onChange= { (event, editor) => {
                        const length = editor.getData().length;
                        this.setState({submitDisabled: length <= 0})
                    }}
                />
    }

    render() {
        return (
            <Container className="mt-5">
                <Row>
                    <Col />
                    <Col xs={7}>
                    <div className="bg-light border border-secondary">
                        {this.renderEditor()}
                    </div>
                        <Button onClick={this.submit} variant="outline-primary" block disabled={this.state.submitDisabled}>Send</Button>
                    </Col>
                    <Col />
                </Row>
            </Container>);
    }
}

export default PostEditor;