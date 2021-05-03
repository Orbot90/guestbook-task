import React, { useState } from 'react'
import Card from 'react-bootstrap/Card'
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import sanitizeHtml from 'sanitize-html';
import Dropdown from 'react-bootstrap/Dropdown'
import { useApplicationContext } from '../ApplicationContext';
import PostEditor from '../editor/PostEditor'

export default function Post(props) {
    const post = props.data

    const data = post.data;
    const date = post.date;
    const username = post.userName;
    const editedBy = post.editedBy;
    const editedDate = post.editedDate;

    const [approved, setApproved] = useState(post.approved)

    const [editing, setEditing] = useState(false)


    const loginService = useApplicationContext().loginService;
    const postService = useApplicationContext().postService

    const userName = loginService.userName
    const roles = loginService.roles


        
    const sanitize = (dirty, options) => ({
    __html: sanitizeHtml(
        dirty, 
        options
    )
    });

    const sanitizeOptions = {
    allowedTags: ['figure', 'img', 'strong', 'i', 'span', 'p'],
    allowedAttributes: {
        '*' : ['class'],
        'span': ['style'],
        'img': ['src']
    },
    disallowedTagsMode: 'escape'
    }
    
    const SanitizeHTML = ({ html, options }) => (
    <div dangerouslySetInnerHTML={sanitize(html, options)} />
    );

    const approvePost = () => {
        postService.approvePost(post, loginService.token, () => setApproved(true))
    }

    const deletePost = () => {
        postService.deletePost(post.id, loginService.token)
    }

    const PostCard = () => {
                    return <div>
                        <Card className="gedf-card mt-5">
                            <div class="card-header">
                                <div class="d-flex justify-content-between align-items-center">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="ml-2">
                                            <div class="h5 m-0">{username}</div>
                                        </div>
                                    </div>
                                    {
                                        approved ? <div /> :
                                            <div class="h7 mb-2 text-danger"> <i class="fa fa-clock-o"></i>Pending approval!</div>
                                        
                                    }
                                    <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>Posted on: {date}</div>
                                    <div>
                                        {
                                            roles && roles.includes("ROLE_ADMIN") ?
                                    <Dropdown>
                                        <Dropdown.Toggle variant="link" id="dropdown-basic">
                                        </Dropdown.Toggle>

                                        <Dropdown.Menu>
                                            <Dropdown.Item onClick={approvePost}>Approve</Dropdown.Item>
                                            <Dropdown.Item onClick={() => setEditing(true)}>Edit</Dropdown.Item>
                                            <Dropdown.Item onClick={deletePost}>Delete</Dropdown.Item>
                                        </Dropdown.Menu>
                                        </Dropdown>
                                        : <div />
                                        }
                                    </div>
                                </div>

                            </div>
                            <div class="card-body">
                                

                                <div class="card-text">
                                    <SanitizeHTML html={data} options={sanitizeOptions} />
                                </div>
                            </div>
                        </Card>
                    </div>
    }

    return (
        <Container className="md-5">
            <Row>
                <Col />
                <Col xs={9}>
                    {editing ?  <PostEditor mode='edit' post={post}
                        onFinishEditing={() => setEditing(false)}/> : <PostCard />}
                </Col>
                <Col />
            </Row>
        </Container>
        )
}