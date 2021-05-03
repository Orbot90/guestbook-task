const prodMode = false

const applicationProperies = {
    prod: {
        imageUploadUrl: 'http://localhost:8080/image',
        applicationHost: 'http://localhost:8080'
    },
    local: {
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