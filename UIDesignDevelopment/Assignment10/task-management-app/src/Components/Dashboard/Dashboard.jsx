import React, { useState } from 'react'
import TaskList from '../TaskList/TaskList'

const Dashboard = ({
    taskList
}) => {


    return (
        <div div className='row mt-3' >
            <div className='col-4 p-1'>
                <TaskList taskList={taskList} status="New" />
            </div> <div className='col-4 p-1'>
                <TaskList taskList={taskList} status="In Progress" />
            </div> <div className='col-4 p-1'>
                <TaskList taskList={taskList} status="Done" />
            </div>
        </div >
    )
}

export default Dashboard