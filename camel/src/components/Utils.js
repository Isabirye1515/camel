const mainApi = "http://localhost:8080/camel/rest";

export const getFromCamel = async (endpoint, callBack) => {
    try {
 
        const response = await fetch(`${mainApi}/${endpoint}`,{
            'method':"GET",
             'headers': {
                "Content-Type": "application/json"
            },
        });

        if (response.ok) {
            const data = await response.json();
            callBack(data);
        } else {
            throw new Error(`HTTP Error: ${response.status}`);
        }

    } catch (error) {
        console.error(error);
    }
};


export const postToCamel = async (
    endpoint,
    payload,
    onSuccess,
    onError
) => {
    try {
        const response = await fetch(`${mainApi}/${endpoint}`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            credentials: "include",
            body: JSON.stringify(payload)
        });

        if (!response.ok) {
            let message = `HTTP Error ${response.status}`;

            try {
                message = await response.text(); // or response.json()
            } catch {}

            throw new Error(message);
        }

        const data = await response.json();
        onSuccess(data);

    } catch (err) {
        console.error(err);

        if (onError) {
            onError(err);
        }
    }
};