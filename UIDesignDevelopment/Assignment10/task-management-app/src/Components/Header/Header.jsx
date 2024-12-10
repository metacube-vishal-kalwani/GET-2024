import React from 'react'
import './Header.scss'
import { Button } from 'react-bootstrap'

const Header = ({
    setModalShow
}) => {
    return (
        <div className='mt-5 p-3 bg-white card row d-flex flex-row align-items-center justify-content-between Shadow'>
            <div className="col-auto card-item">
                <p className='mb-0 text-body-emphasis projectName'>Task Management APP</p>
            </div>
            <div className="col-auto card-item">
                <Button className='btn-dark' onClick={() => setModalShow(true)}>
                    Add New Task
                </Button>
            </div>
        </div>
    )
}

export default Header