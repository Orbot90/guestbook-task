import './PostEditor.css'
import React from 'react';
import Editor from '@ckeditor/ckeditor5-build-inline/build/ckeditor';
import {CKEditor} from '@ckeditor/ckeditor5-react'
import Container from 'react-bootstrap/Container'
import Row from 'react-bootstrap/Row'
import Col from 'react-bootstrap/Col'
import Button from 'react-bootstrap/Button'
import {getApplicationProperty} from '../properties/applicationProperties'
import ButtonGroup from 'react-bootstrap/ButtonGroup'
import Card from 'react-bootstrap/Card'
import FormGroup from 'react-bootstrap/FormGroup'
import ButtonToolbar from 'react-bootstrap/ButtonToolbar'

class PostEditor extends React.Component {

    constructor(props) {
        super(props);
        this.imageUploadUrl = getApplicationProperty('imageUploadUrl')
        this.submit = this.submit.bind(this)
        this.ckeditor = this.ckeditor.bind(this)

        this.charactersLimit = 400

        this.state = {
            submitDisabled: true,
            charactersCount: 0,
            charactersLimitExceeded: false
        }
    }

    submit() {
        const data = this.editor.getData()
        console.log(data)
    }

    ckeditor() {
        return <div className="border border-light bg-white rounded"><CKEditor
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
                        const isLimitExceeded = length > this.charactersLimit;

                        this.setState({
                            charactersCount: length,
                            submitDisabled: (isLimitExceeded || length == 0),
                            charactersLimitExceeded: isLimitExceeded
                        })

                    }}
                /></div>
    }

    renderEditor() {

        return (
            <Card className="bg-light rounded">
                    <div class="card-header">
                        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Share some thoughts</a>
                            </li>
                        </ul>
                    </div>

                <FormGroup>
                    {this.ckeditor()}
                </FormGroup>
                <ButtonToolbar className="justify-content-between">
                    <ButtonGroup>
                        <Button className="m-2" onClick={this.submit} varian="primary" 
                        block disabled={this.state.submitDisabled}>Share</Button>
                    </ButtonGroup>
                    {
                        this.state.charactersLimitExceeded ?
                        <span className="mr-5 warningtext">{this.state.charactersCount}\{this.charactersLimit}</span> :
                    <span className="mr-5 words-counter">{this.state.charactersCount}\{this.charactersLimit}</span>
                    }
                </ButtonToolbar>
            </Card>
            )
    }

    render() {
        return (
            <Container className="mt-5">
                <Row>
                    <Col />
                    <Col xs={7}>
                    <div>
                        {this.renderEditor()}
                    </div>
                    </Col>
                    <Col />
                </Row>
            </Container>);
    }
}

export default PostEditor;