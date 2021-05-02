import React from 'react'
import Card from 'react-bootstrap/Card'
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import Container from "react-bootstrap/Container";

class Post extends React.Component {

    constructor(props) {
        super(props);

        this.data = props.data;
        this.date = props.date;
        this.username = props.username;
        this.editedBy = props.editedBy;
        this.editedDate = props.editedDate;
        this.approved = props.approved;
    }

    render() {
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
                                                <div class="h5 m-0">JotaroKujo</div>
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
                                    <div class="text-muted h7 mb-2"> <i class="fa fa-clock-o"></i>May, 01, 2021</div>

                                    <p class="card-text">
                                        Lorem ipsum dolor sit amet consectetur adipisicing elit. Quo recusandae nulla rem eos ipsa praesentium esse magnam nemo dolor
                                        sequi fuga quia quaerat cum, obcaecati hic, molestias minima iste voluptates.
                                    </p>
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