import { createContext, useContext } from 'react';
import React from 'react'
import {prepareContext} from './ContextConfiguration'

const ApplicationContext = createContext({});
const value = prepareContext();

export const ApplicationProvider = (props) => {
    return <ApplicationContext.Provider value={value}>
        {props.children}
    </ApplicationContext.Provider>
}

export const useApplicationContext = () => {
    return useContext(ApplicationContext);
}