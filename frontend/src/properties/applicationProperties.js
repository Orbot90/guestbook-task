const prodMode = false

const applicationProperies = {
    prod: {
        imageUploadUrl: 'produrl'
    },
    local: {
        imageUploadUrl: 'http://localhost:8080/image'
    }
}

export function getApplicationProperty(name) {
    if (prodMode) {
        return applicationProperies.prod[name]
    } else {
        return applicationProperies.local[name]
    }
}