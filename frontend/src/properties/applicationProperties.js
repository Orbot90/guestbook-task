const prodMode = false

const applicationProperies = {
    local: {
        imageUploadUrl: 'http://localhost:8080/image',
        applicationHost: 'http://localhost:8080'
    },
    prod: {
        imageUploadUrl: 'http://35.233.110.226:8080/image',
        applicationHost: 'http://35.233.110.226:8080'
    }
}

export function getApplicationProperty(name) {
    if (prodMode) {
        return applicationProperies.prod[name]
    } else {
        return applicationProperies.local[name]
    }
}