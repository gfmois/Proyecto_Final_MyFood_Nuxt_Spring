export function capitalize (value: String) {
    if (!value) {
        return ''
    }

    return value[0].toUpperCase() + value.slice(1);
}
