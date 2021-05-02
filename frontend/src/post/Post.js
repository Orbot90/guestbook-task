import React from 'react'
import Card from 'react-bootstrap/Card'
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";
import sanitizeHtml from 'sanitize-html';

class Post extends React.Component {

    constructor(props) {
        super(props);
        const post = props.data

        this.data = post.data;
        this.date = post.date;
        this.username = post.userName;
        this.editedBy = props.editedBy;
        this.editedDate = props.editedDate;
        // this.approved = props.approved;
    }

    render() {

          
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

        return (
            <Container className="md-5">
                <Row>
                    <Col />
                    <Col xs={9}>
                        <div>
                            <Card className="gedf-card mt-5">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between align-items-center">
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div class="ml-2">
                                                <div class="h5 m-0">{this.username}</div>
                                            </div>
                                        </div>
                                        <div>
                                            <div class="dropdown">
                                                <button class="btn btn-link dropdown-toggle" type="button" id="gedf-drop1"
                                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                                    <i class="fa fa-ellipsis-h"></i>
                                                </button>
                                                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="gedf-drop1">
                                                    <a class="dropdown-item" href="#">Approve</a>
                                                    <a class="dropdown-item" href="#">Edit</a>
                                                    <a class="dropdown-item" href="#">Delete</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                </div>
                                <div class="card-body">
                                    <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>{this.date}</div>

                                    <div class="card-text">
                                        <SanitizeHTML html={this.data} options={sanitizeOptions} />
                                    </div>
                                </div>
                            </Card>
                        </div>
                    </Col>
                    <Col />
                </Row>
            </Container>
            )
    }
}

export default Post;