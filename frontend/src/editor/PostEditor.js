import './PostEditor.css'
import React, { useState } from 'react';
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
import { useApplicationContext } from '../ApplicationContext'

export default function PostEditor() {

    
    const postService = useApplicationContext().postService
    const loginService = useApplicationContext().loginService

    const [editorInstance, setEditorInstance] = useState(null)

    const imageUploadUrl = getApplicationProperty('imageUploadUrl')
    const charactersLimit = 400
    const [isLoggedIn, setLoggedIn] = useState(false)
    const [submitDisabled, setSubmitDisabled] = useState(true)
    const [charactersCount, setCharactersCount] = useState(0)
    const [charactersLimitExceeded, setCharactersLimitExceeded] = useState(false)

    loginService.addSignInListener(() => {
        setLoggedIn(true)
    })

    loginService.addSignOutListener(() => {
        setLoggedIn(false)
    })

    const submit = () => {
        postService.submitPost(editorInstance, loginService.userName)
    }
    const ckeditor = () => {
        return <div className="border border-light bg-white rounded"><CKEditor
                    editor={ Editor }
                    config={ {
                            placeholder: "Type your text here",
                            simpleUpload: {
                                uploadUrl: imageUploadUrl
                            },
                            wordCount: {
                                onUpdate: stats => {
                                    const length = stats.characters
                                    const isLimitExceeded = length > charactersLimit;

                                    setCharactersCount(length)
                                    setSubmitDisabled(isLimitExceeded || length == 0)
                                    setCharactersLimitExceeded(isLimitExceeded)
                                }
                            }
                        }
                    }
                    onReady={ (editor) => {
                        setEditorInstance(editor)
                    }
                    }
                /></div>
    }

    const renderEditor = () => {

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
                    {ckeditor()}
                </FormGroup>
                <ButtonToolbar className="justify-content-between">
                    <ButtonGroup>
                        <Button className="m-2" onClick={submit} variant="primary" 
                        block disabled={submitDisabled}>Share</Button>
                    </ButtonGroup>
                    {
                        charactersLimitExceeded ?
                        <span className="mr-5 warningtext">{charactersCount}\{charactersLimit}</span> :
                    <span className="mr-5 words-counter">{charactersCount}\{charactersLimit}</span>
                    }
                </ButtonToolbar>
            </Card>
            )
    }

    return isLoggedIn ? (
        <Container className="mt-5 md-5">
            <Row>
                <Col />
                <Col xs={7}>
                <div>
                    {renderEditor()}
                </div>
                </Col>
                <Col />
            </Row>
        </Container>) : <div />;
}