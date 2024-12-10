import React, { useState } from "react";
import { Button, Modal } from "react-bootstrap";
import "./NewTask.scss";

const NewTask = (props) => {
    const initTaskData = {
        title: "",
        desc: "",
        priority: 0,
        completionDate: "",
        creationDate: "",
    };

    const [taskData, setTaskData] = useState(initTaskData);

    return (
        <Modal
            {...props}
            size="lg"
            aria-labelledby="contained-modal-title-vcenter"
            centered
        >
            <Modal.Header closeButton>
                <Modal.Title id="contained-modal-title-vcenter">New Task</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <div className="FormContainer">
                    <form className="Form">
                        <label htmlFor="title">
                            <h5>Title</h5>
                            <input
                                type="text"
                                placeholder="Bug fix in frontEnd"
                                required
                                onChange={(e) =>
                                    setTaskData((prev) => ({ ...prev, title: e.target.value }))
                                }
                            />
                        </label>
                        <label htmlFor="desc">
                            <h5>Description</h5>
                            <textarea
                                cols={40}
                                rows={5}
                                placeholder="Glitch in state management...."
                                onChange={(e) =>
                                    setTaskData((prev) => ({ ...prev, desc: e.target.value }))
                                }
                            ></textarea>
                        </label>
                        <label htmlFor="priority">
                            <h5>Priority</h5>
                            <select
                                onChange={(e) =>
                                    setTaskData((prev) => ({
                                        ...prev,
                                        priority: parseInt(e.target.value),
                                    }))
                                }
                            >
                                <option value="0">High</option>
                                <option value="1">Moderate</option>
                                <option value="2">Low</option>
                            </select>
                        </label>
                    </form>
                </div>
            </Modal.Body>
            <Modal.Footer>
                <Button onClick={props.onHide}>Close</Button>
                <Button
                    className="btn btn-dark"
                    onClick={() => props.createTask(taskData)}
                >
                    Create
                </Button>
            </Modal.Footer>
        </Modal>
    );
};

export default NewTask;