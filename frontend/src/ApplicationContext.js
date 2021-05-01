import { createContext, useContext } from 'react';
import React from 'react'
import {prepareContext} from './ContextConfiguration'

const ApplicationContext = createContext(null);

export const ApplicationProvider = (props) => {
    const value = prepareContext();

    return <ApplicationContext.Provider value={value}>
        {props.children}
    </ApplicationContext.Provider>
}

export const useApplicationContext = () => {
    return useContext(ApplicationContext);
}