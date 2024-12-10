import React, { useEffect } from 'react'
import { Button } from 'react-bootstrap'
import './TaskList.scss'
const TaskList = ({
    taskList,
    status
}) => {

    const bgColors = ['rgb(255 71 55 / 71%)', 'rgb(255 255 71 / 70%)', '#90ee90b3'] // high , medium , low

    // useEffect(() => {

    // }, [])
    return (
        <div className='card Shadow bg-white p-2 '>
            <div className="d-flex align-items-center justify-content-between flex-row">
                <p className='mb-0 fw-bold' style={{ fontSize: '1.3rem' }}>{status}</p>

            </div>
            <div className='mt-5 d-flex flex-column list' >
                {
                    taskList?.filter((task) => (task.taskStatus === status))?.map((task, idx) => (
                        <div id={idx} className='idx border-0 taskCard p-2 Shadow my-1 ' style={{ backgroundColor: bgColors[task?.priority] }}>
                            <div className='d-flex flex-column gap-1'>
                                <span>
                                    {task?.title}
                                </span>
                                <span>
                                    {
                                        (task?.desc.length > 15 ?
                                            task.desc.slice(0, 15) + "..." : task.desc
                                        )
                                    }
                                </span>
                            </div>

                            <div>
                                <button className='btn btn-outline-dark btnText'>Open</button>
                            </div>
                        </div>
                    ))
                }
            </div>
        </div>
    )
}

export default TaskList